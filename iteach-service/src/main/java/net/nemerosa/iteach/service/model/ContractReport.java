package net.nemerosa.iteach.service.model;

import lombok.Data;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ContractReport {

    private final int id;
    private final String name;
    private final Money hourlyRate;
    private final BigDecimal vatRate;
    private final BigDecimal hours;
    private final Money income;
    private final Money incomeVat;
    private final Money incomeTotal;
    private final List<StudentReport> students;

}
