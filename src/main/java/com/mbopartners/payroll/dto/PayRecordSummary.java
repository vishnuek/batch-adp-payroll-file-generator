package com.mbopartners.payroll.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class PayRecordSummary {

    private Integer memberNumber;
    private LocalDate payrollDate;
    private Double amount;
    private Double quantity;
    private Double payRate;
    private String payRateUnit;
    private String type;
    private String workOrderNumber;
    private LocalDate timePeriodStartDate;
    private LocalDate timePeriodEndDate;
    private Long jobId;

    public Date getPayrollDateAsDate() {
        return Date.from(payrollDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getTimePeriodStartDateAsDate() {
        return Date.from(timePeriodStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getTimePeriodEndDateAsDate() {
        return Date.from(timePeriodEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
