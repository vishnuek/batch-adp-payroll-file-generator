package com.mbopartners.payroll.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Data
public class PayRecord {
    private Integer memberNumber;
    private LocalDate payrollDate;
    private String description;
    private Double amount;
    private Double quantity;
    private Double payRate;
    private String payRateUnit;
    private String type;
    private String workOrderNumber;
    private String workOrderName;
    private LocalDate workOrderStartDate;
    private LocalDate workOrderEndDate;
    private String workOrderEndDateType;
    private String workOrderWeekEndingDay;
    private LocalDate timePeriodStartDate;
    private LocalDate timePeriodEndDate;
    private Long jobId;

    public Date getPayrollDateAsDate() {
        return Date.from(payrollDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getWorkOrderStartDateAsDate() {
        return workOrderStartDate != null ? Date.from(workOrderStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    public Date getWorkOrderEndDateAsDate() {
        return workOrderEndDate != null ? Date.from(workOrderEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    public Date getTimePeriodStartDateAsDate() {
        return Date.from(timePeriodStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Date getTimePeriodEndDateAsDate() {
        return Date.from(timePeriodEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}


