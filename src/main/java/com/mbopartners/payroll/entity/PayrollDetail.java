package com.mbopartners.payroll.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Setter
@Entity
@ToString
@Table(name = "EXP_PAYROLL_DETAIL", schema = "VAP") // TODO move schema to configuration
public class PayrollDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MEMBER_NUMBER")
    private String memberNumber;
    @Column(name = "PAYROLL_DATE")
    private LocalDate payrollDate;
    @Column(name = "GROSSPAYROLL")
    private double grossAmount;
    @Column(name = "NETPAYROLL")
    private double netAmount;
    @Column(name = "FREQUENCY")
    private String frequency;
    @Column(name = "LIVED_CODE")
    private String livedInCode;
    @Column(name = "WORKED_CODE")
    private String workedInCode;
    @Column(name = "FEDWHAM")
    private double federalWithholdingAmount;
    @Column(name = "STATEWHAM")
    private double stateWithholdingAmount;
    @Column(name = "LSTATEWHAM")
    private double lStateWithholdingAmount;
    @Column(name = "COUNTYWHAM")
    private double countyWithholdingAmount;
    @Column(name = "CITYWHAM")
    private double cityWithholdingAmount;
    @Column(name = "FEDEXAM")
    private double federalExemptAmount;
    @Column(name = "BENEFICIARY_REGISTEREDFLAG")
    private boolean beneficiaryRegisteredFlag;
    @Column(name = "J_MEDICALINS")
    private double jMedicalInsuranceAmount;
    @Column(name = "B_DISABILITYINS")
    private double bDisabilityInsuranceAmount;
    @Column(name = "I_LIFEINS")
    private double iLifeInsuranceAmount;
    @Column(name = "D_DENTALINS")
    private double dDentailInsuranceAmount;
    @Column(name = "ELEVEN_ROTH_401K")
    private double elevenRoth401kAmount;
    @Column(name = "K_EMPLOYEE401K")
    private double kEmployee401kAmount;
    @Column(name = "N_NETADVANCE")
    private double nNetAdvanceAmount;
    @Column(name = "G_GARNISHMENT1")
    private double gGarnishment1Amount;
    @Column(name = "FIFTYONE_GARNISHMENT2")
    private double fiftyOneGarnish2Amount;
    @Column(name = "FIFTYTWO_GARNISHMENT3")
    private double fiftyTwoGarnish3Amount;
    @Column(name = "FIFTYTHREE_GARNISHMENT4")
    private double fiftyThreeGarnish4Amount;
    @Column(name = "FIVEFOUR_GARNISHMENT5")
    private double fiftyFourGarnish5Amount;
    @Column(name = "L_401K_LOAN2")
    private double l401kLoan2Amount;
    @Column(name = "FORTYONE_401K_LOAN1")
    private double fortyOne401kLoan1Amount;
    @Column(name = "D_PAYROLLADD2")
    private double dPayrollAdd2Amount;
    @Column(name = "C_AMEX1401")
    private double cAmex1401Amount;
    @Column(name = "R_AMEX2989")
    private double rAmex2989Amount;
    @Column(name = "M_MBOPMT1429")
    private double mMboPmt1429Amount;
    @Column(name = "T_TTPMT1430")
    private double tTtPmt1430Amount;
    @Column(name = "P_SUPPLI")
    private double pSuppliAmount;
    @Column(name = "Q_SUPPLISP")
    private double qSupplispAmount;
    @Column(name = "QP_PARKING_PRE")
    private double qpParkingPreAmount;
    @Column(name = "QPP_PARKING_POST")
    private double qppParkingPostAmount;
    @Column(name = "QT_TRANSIT_PRE")
    private double qtTransitPreAmount;
    @Column(name = "QTP_TRANSIT_POST")
    private double qtpTransitPostAmount;
    @Column(name = "F_LST")
    private double fLocalServicesTaxAmount;
    @Column(name = "SUIDI")
    private double suidiAmount;
    @Column(name = "MIE")
    private double mieAmount;
    @Column(name = "MIS")
    private double misAmount;
    @Column(name = "MIC")
    private double micAmount;
    @Column(name = "MIF")
    private double mifAmount;
    @Column(name = "CIE")
    private double cieAmount;
    @Column(name = "CIS")
    private double cisAmount;
    @Column(name = "CIC")
    private double cicAmount;
    @Column(name = "CIF")
    private double cifAmount;
    @Column(name = "JOB_ID")
    private Long jobId;

    public Date getPayrollDateAsDate() {
        return Date.from(payrollDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}


