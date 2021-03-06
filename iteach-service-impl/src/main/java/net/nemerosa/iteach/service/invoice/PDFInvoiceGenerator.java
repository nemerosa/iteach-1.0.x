package net.nemerosa.iteach.service.invoice;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import net.nemerosa.iteach.common.UntitledDocument;
import net.nemerosa.iteach.service.InvoiceGenerationException;
import net.nemerosa.iteach.service.model.ContractReport;
import net.nemerosa.iteach.service.model.InvoiceData;
import net.nemerosa.iteach.service.model.StudentReport;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.Money;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static com.itextpdf.text.Element.ALIGN_RIGHT;
import static java.lang.String.format;

@Component
public class PDFInvoiceGenerator implements InvoiceGenerator {

    private static final Font section = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Font amount = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
    private static final Font amountTotal = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    public static final int TABLE_WIDTH = 75;
    public static final int AMOUNT_BORDER_WIDTH = 2;
    public static final int PADDING = 5;

    @Override
    public String getType() {
        return "application/pdf";
    }

    @Override
    public byte[] generate(InvoiceData data, Locale locale) {
        // TODO Using the locale for the generation
        locale = Locale.ENGLISH;
        // Creates the output
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // Creates the document
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            // Opens the document
            document.open();
            // Meta-information
            document.addAuthor(data.getTeacherName());
            document.addCreator("iTeach");
            document.addTitle(data.getTitle());

            int margin = 20;
            document.setMargins(margin, margin, margin, margin);

            @SuppressWarnings("deprecation")
            Chunk tab1 = new Chunk(new VerticalPositionMark(), 150, false);

            // Company logo
            UntitledDocument logo = data.getCompanyLogo();
            if (logo != null) {
                Image image = Image.getInstance(logo.getContent());
                float width = image.getWidth();
                float height = image.getHeight();
                if (width > 400) {
                    height = height * 400f / width;
                    image.scaleToFit(400f, height);
                }
                Paragraph p = new Paragraph();
                p.add(image);
                p.setSpacingBefore(50);
                p.setSpacingAfter(20);
                document.add(p);
            }

            document.add(header(data));
            document.add(getInvoicePara(data, locale, tab1));
            if (data.isDetailPerStudent()) {
                document.add(studentDetail(data, locale));
            }
            document.add(total(data, locale));
            document.add(coordinates(data));

            // End of the document
            document.close();

            // OK
            return out.toByteArray();
        } catch (IOException | DocumentException e) {
            throw new InvoiceGenerationException(e);
        }
    }

    private Element coordinates(InvoiceData data) {
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100);

        // Line 1
        table.addCell(cell().withText(data.getProfile().getCompany()).done());
        table.addCell(cell().withText("Tel:").withAlign(ALIGN_RIGHT).done());
        table.addCell(cell().withText(data.getProfile().getPhone()).withAlign(ALIGN_RIGHT).done());

        // Line 2
        table.addCell(cell().withText(data.getProfile().getPostalAddress()).withRowspan(2).done());
        table.addCell(cell().withText("Email:").withAlign(ALIGN_RIGHT).done());
        table.addCell(cell().withText(data.getTeacherEmail()).withAlign(ALIGN_RIGHT).done());

        // Line 3
        table.addCell(cell().withText("VAT:").withAlign(ALIGN_RIGHT).done());
        table.addCell(cell().withText(data.getProfile().getVat()).withAlign(ALIGN_RIGHT).done());

        // Line 4
        table.addCell(cell().withText("IBAN:").withAlign(ALIGN_RIGHT).withColspan(2).done());
        table.addCell(cell().withText(data.getProfile().getIban()).withAlign(ALIGN_RIGHT).done());

        // Line 5
        table.addCell(cell().withText("BIC:").withAlign(ALIGN_RIGHT).withColspan(2).done());
        table.addCell(cell().withText(data.getProfile().getBic()).withAlign(ALIGN_RIGHT).done());

        // Container
        PdfPTable section = new PdfPTable(1);
        section.setSpacingBefore(50);
        section.setWidthPercentage(100);
        section.addCell(table);

        // OK for the table
        return section;
    }

    private Paragraph total(InvoiceData data, Locale locale) {
        Paragraph p = new Paragraph();
        p.add(new Paragraph("Total", section));

        PdfPTable table = new PdfPTable(7);
        table.setWidthPercentage(100.0f);

        // Header line
        table.addCell(cell().withText("").done());
        table.addCell(cell().withText("Hours").withAlign(Element.ALIGN_RIGHT).done());
        table.addCell(cell().withText("Hourly rate").withAlign(Element.ALIGN_RIGHT).done());
        table.addCell(cell().withText("Total").withAlign(Element.ALIGN_RIGHT).done());
        table.addCell(cell().withText("VAT%").withAlign(Element.ALIGN_RIGHT).done());
        table.addCell(cell().withText("VAT").withAlign(Element.ALIGN_RIGHT).done());
        table.addCell(cell().withText("Total w/ VAT").withAlign(Element.ALIGN_RIGHT).done());

        // One line per contract
        for (ContractReport contractReport : data.getReport().getContracts()) {
            // Name
            table.addCell(cell().withText(contractReport.getName()).withAlign(Element.ALIGN_RIGHT).withPadding(PADDING).done());
            // Hours
            table.addCell(cell()
                            .withText(formatHours(contractReport.getHours(), locale))
                            .withFont(amount)
                            .withAlign(Element.ALIGN_RIGHT)
                            .withPadding(PADDING)
                            .done()
            );
            // Hourly rate
            table.addCell(cell()
                            .withText("" + contractReport.getHourlyRate())
                            .withFont(amount)
                            .withAlign(Element.ALIGN_RIGHT)
                            .withPadding(PADDING)
                            .done()
            );
            // Total
            table.addCell(amount(contractReport.getIncome()).done());
            // VAT rate & VAT
            if (contractReport.getVatRate() != null) {
                table.addCell(cell().withFont(amount).withText(format(locale, "%s%%", contractReport.getVatRate())).withAlign(Element.ALIGN_RIGHT).withPadding(PADDING).done());
                table.addCell(amount(contractReport.getIncomeVat()).done());
            } else {
                table.addCell(cell().withFont(amount).withText("-").withAlign(Element.ALIGN_RIGHT).withPadding(PADDING).done());
                table.addCell(cell().withText("-").withFont(PDFInvoiceGenerator.amount).withAlign(ALIGN_RIGHT).withPadding(PADDING).done());
            }
            // Total
            table.addCell(amount(contractReport.getIncomeTotal()).done());
        }

        // Last line: overall total
        filler(table, 6);
        table.addCell(amount(data.getReport().getIncomeTotal()).withFont(amountTotal).withBorderWidth(AMOUNT_BORDER_WIDTH).done());

        p.add(table);

        // Message
        p.add(new Paragraph("The payment is to be made to the following account:"));
        p.add(
                new Paragraph(
                        format(
                                "IBAN %s (BIC %s) held by %s",
                                data.getProfile().getIban(),
                                data.getProfile().getBic(),
                                data.getProfile().getCompany()
                        )
                )
        );

        /*
         * <p>The payment is to be made to the following account:</p>

                <p>
                    IBAN <span class="it-invoice-iban">{{invoice.profile.iban}}</span> with
                    BIC <span class="it-invoice-bic">{{invoice.profile.bic}}</span>,
                    held by <span class="it-invoice-company">Nemerosa Sprl</span>.</p>
         */

        // OK
        return p;
    }

    private CellBuilder amount(Money amount) {
        return cell()
                .withText(amount.toString())
                .withFont(PDFInvoiceGenerator.amount)
                .withAlign(ALIGN_RIGHT)
                .withPadding(PADDING);
    }

    private CellBuilder cell() {
        return CellBuilder.create();
    }

    private void filler(PdfPTable table, int colspan) {
        PdfPCell filler = cell("");
        filler.setColspan(colspan);
        table.addCell(filler);
    }

    private Paragraph studentDetail(InvoiceData data, Locale locale) {
        // Details of students
        Paragraph p = new Paragraph();
        p.add(new Paragraph("Detail per student", section));

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(TABLE_WIDTH);
        for (ContractReport contractReport : data.getReport().getContracts()) {
            for (StudentReport student : contractReport.getStudents()) {
                String name = student.getName();
                if (StringUtils.isNotBlank(contractReport.getName())) {
                    name += format(" (%s)", contractReport.getName());
                }
                table.addCell(
                        cell()
                                .withText(name)
                                .withPadding(PADDING)
                                .done()
                );
                table.addCell(
                        cell()
                                .withText(formatHours(student.getHours(), locale) + " hours")
                                .withAlign(ALIGN_RIGHT)
                                .withPadding(PADDING)
                                .done()
                );
                filler(table, 1);
            }
        }
        p.add(table);
        return p;
    }

    public static String formatHours(BigDecimal hours, Locale locale) {
        NumberFormat format = DecimalFormat.getNumberInstance(locale);
        format.setMinimumFractionDigits(0);
        format.setMaximumFractionDigits(1);
        return format.format(hours);
    }

    private Element header(InvoiceData data) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        // From/To
        table.addCell(cell("From:"));
        table.addCell(cell("To:"));
        // Company -- School name
        table.addCell(cell(data.getProfile().getCompany()));
        table.addCell(cell(data.getSchool().getName()));
        // Addresses
        table.addCell(cell(data.getProfile().getPostalAddress()));
        table.addCell(cell(data.getSchool().getPostalAddress()));
        // VAT
        table.addCell(cell("VAT: " + data.getProfile().getVat()));
        table.addCell(cell("VAT: " + data.getSchool().getVat()));

        PdfPTable section = new PdfPTable(1);
        section.setWidthPercentage(100);
        section.addCell(table);

        // OK for the table
        return section;
    }

    private PdfPCell cell(String text) {
        return cell(text, Element.ALIGN_LEFT);
    }

    private PdfPCell cell(String text, int alignment) {
        return cell().withText(text).withAlign(alignment).done();
    }

    private Paragraph getInvoicePara(InvoiceData data, Locale locale, Chunk tab1) {
        Paragraph p = new Paragraph();
        p.add(new Paragraph("Invoice", section));
        p.add(new Paragraph(""));
        p.add(tabbedLine(tab1, "Invoice number:", String.valueOf(data.getNumber())));
        p.add(tabbedLine(tab1, "Invoice date:", DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale).format(data.getDate())));
        p.add(tabbedLine(tab1, "Work carried out by:", data.getTeacherName()));
        p.add(tabbedLine(tab1, "Period:", DateTimeFormatter.ofPattern("MMMM yyyy", locale).format(data.getPeriod())));
        if (StringUtils.isNotBlank(data.getComment())) {
            p.add(new Paragraph(""));
            p.add(new Paragraph(data.getComment()));
        }
        return p;
    }

    private Paragraph tabbedLine(Chunk tab1, String label, String value) {
        Paragraph line = new Paragraph();
        line.add(new Chunk(label));
        line.add(tab1);
        line.add(new Chunk(value));
        return line;
    }
}
