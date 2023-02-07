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

@Getter
@Setter
@Entity
@ToString
@Table(name = "EXP_PAY_RECORD_SUMMARY", schema = "VAP") // TODO move schema to configuration
public class PayRecordSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "MEMBER_NUMBER")
    private String memberNumber;
    @Column(name = "PAYROLL_DATE")
    private LocalDate payrollDate;
    @Column(name = "AMOUNT")
    private Double amount;
    @Column(name = "QUANTITY")
    private Double quantity;
    @Column(name = "PAYRATE")
    private Double payRate;
    @Column(name = "PAYRATE_UNIT")
    private String payRateUnit;
    @Column(name = "TRANS_TYPE")
    private String type;
    @Column(name = "WO_NUMBER")
    private String workOrderNumber;
    @Column(name = "TIME_PERIOD_START_DATE")
    private LocalDate timePeriodStartDate;
    @Column(name = "TIME_PERIOD_END_DATE")
    private LocalDate timePeriodEndDate;
    @Column(name = "JOB_ID")
    private Long jobId;

}
