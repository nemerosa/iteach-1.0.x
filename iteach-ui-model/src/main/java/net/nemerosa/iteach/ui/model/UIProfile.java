package net.nemerosa.iteach.ui.model;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class UIProfile {

    @Size(max = 80)
    private final String company;
    @Size(max = 200)
    @URL
    private final String companyLogo;
    @Size(max = 200)
    private final String postalAddress;
    @Size(max = 40)
    private final String phone;
    @Size(max = 40)
    private final String vat;
    @Size(max = 80)
    private final String iban;
    @Size(max = 10)
    private final String bic;
    @Min(0L)
    private final Long invoiceLastNb;

}
