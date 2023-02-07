package com.mbopartners.payroll.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class PayrollDetail {

    private Integer memberNumber;
    private LocalDate payrollDate;
    private double gross;
    private double net;
    private String frequency;
    private String livedInCode;
    private String workedInCode;
    private double federalWithholdingAmount;
    private double stateWithholdingAmount;
    private double lStateWithholdingAmount;
    private double countyWithholdingAmount;
    private double cityWithholdingAmount;
    private double federalExemptAmount;
    private boolean beneficiaryRegisteredFlag;
    private double jMedicalInsuranceAmount;
    private double bDisabilityInsuranceAmount;
    private double iLifeInsuranceAmount;
    private double dDentailInsuranceAmount;
    private double elevenRoth401kAmount;
    private double kEmployee401kAmount;
    private double nNetAdvanceAmount;
    private double gGarnishment1Amount;
    private double fiftyOneGarnish2Amount;
    private double fiftyTwoGarnish3Amount;
    private double fiftyThreeGarnish4Amount;
    private double fiftyFourGarnish5Amount;
    private double l401kLoan2Amount;
    private double fortyOne401kLoan1Amount;
    private double dPayrollAdd2Amount;
    private double cAmex1401Amount;
    private double rAmex2989Amount;
    private double mMboPmt1429Amount;
    private double tTtPmt1430Amount;
    private double pSuppliAmount;
    private double qSupplispAmount;
    private double qpParkingPreAmount;
    private double qppParkingPostAmount;
    private double qtTransitPreAmount;
    private double qtpTransiPostAmount;
    private double fLocalServicesTaxAmount;
    private double suidiAmount;
    private double mieAmount;
    private double misAmount;
    private double micAmount;
    private double mifAmount;
    private double cieAmount;
    private double cisAmount;
    private double cicAmount;
    private double cifAmount;
    private Long jobId;

    public Date getPayrollDateAsDate() {
        return Date.from(payrollDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
