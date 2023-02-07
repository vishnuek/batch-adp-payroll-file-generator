package com.mbopartners.payroll.tasklets;

import com.mbopartners.payroll.dto.PayRecord;
import com.mbopartners.payroll.dto.TimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.LocalDate.now;

/**
 * Parse the time period from the description if possible
 */
@Slf4j
@Component
public class TimePeriodDescriptionParser {

    private static final String MM_DD_YYYY = "MM/dd/yyyy";
    private static final String regex = "\\d{2}/\\d{2}/\\d{4}";
    private static final String regex2 = "[a-zA-Z]{3} \\d{1,2}, \\d{4}";

    // Pay for 03/03/2021 for 8.0 Hour at 43.13 (Regular time)
    // Pay for 02/22/2021 - 02/28/2021 for 3.0 Hour at 25.0 (Regular time)
    private static final String descriptionPattern1 = "^Pay for \\d{2}/\\d{2}/\\d{4} .*$";

    // 1.5 hours ST at $92.0/hour on Feb 25, 2021
    // 32.5 hours ST at $125.0/hour on Mar 1, 2021 - Mar 5, 2021
    // UHG - FG Transition - E&I - Medical Director - ST - Oct 26, 2020 - Oct 31, 2020
    private static final String descriptionPattern2 = "^.* [a-zA-Z]{3} \\d{1,2}, \\d{4}.*$";

    public TimePeriod parse(PayRecord payRecord) {


        TimePeriod timePeriod = null;
        if (payRecord.getWorkOrderStartDate() != null && payRecord.getWorkOrderEndDate() != null && payRecord.getWorkOrderWeekEndingDay() != null) {
            // FB AML Supplement Pay 2/22/2021-2/28/2021

            // Chris Hermann (10728)
            // Chris E. Hermann- NP- Flint, MI (Hourly) (547311)
            // 2/8 - 2/9
            // 16 hours @ $5.50/hr
            var matcher = Pattern.compile(descriptionPattern1).matcher(payRecord.getDescription());
            var matcher2 = Pattern.compile(descriptionPattern2).matcher(payRecord.getDescription());

            final LocalDate endDate;
            final LocalDate startDate;

            if (matcher.find()) {
                Matcher m = Pattern.compile(regex).matcher(payRecord.getDescription());
                if (m.find()) {
                    startDate = LocalDate.parse(m.group(0), DateTimeFormatter.ofPattern(MM_DD_YYYY));
                } else {
                    log.error("Error parsing date from description: payRecord - {}", payRecord);
                    throw new RuntimeException("Error parsing date from description: payRecord - . " + payRecord);
                }
            } else if (matcher2.find()) {
                Matcher m = Pattern.compile(regex2).matcher(payRecord.getDescription());
                if (m.find()) {
                    startDate = LocalDate.parse(m.group(0), DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
                } else {
                    log.error("Error parsing date from description: payRecord - {}", payRecord);
                    throw new RuntimeException("Error parsing date from description: payRecord - . " + payRecord);
                }
            } else {
                // Put into current or last time period
                log.warn("Unable to match pattern to parse date from description. Setting time period to current or last time period: payRecord - {}", payRecord);
                if (now().isAfter(payRecord.getWorkOrderEndDate())) {
                    startDate = payRecord.getWorkOrderEndDate();
                    log.warn("Work order has ended. Setting time period to last time period of work order. startDate - {}", startDate);
                } else {
                    startDate = now();
                    log.warn("Work order is still in progress. Setting time period to current time period. startDate - {}", startDate);
                }
            }

            // place into a time period that is kind of right based on the work order week ending day and parsed start date
            LocalDate timePeriodStartDate = expandStartIfNeeded(startDate, payRecord.getWorkOrderWeekEndingDay());
            timePeriod = new TimePeriod(timePeriodStartDate, timePeriodStartDate.plusDays(6));

        } else {
            //make it last friday since we don't know
            timePeriod = new TimePeriod(payRecord.getPayrollDate().minusWeeks(2).plusDays(1), payRecord.getPayrollDate().minusWeeks(1));
            log.warn("Pay record does not have work order information. Setting time period to payroll date - 6 days through payroll date: {} - {}, {}", timePeriod.getStartDate(), timePeriod.getEndDate(), payRecord);
        }

        return timePeriod;
    }

    private LocalDate expandStartIfNeeded(LocalDate start, String weekEndingDay) {
        if (start.getDayOfWeek().getValue() - (DayOfWeek.valueOf(weekEndingDay).getValue() % DayOfWeek.values().length) != 1) {
            start = start.minusWeeks(1).with(DayOfWeek.valueOf(weekEndingDay)).plusDays(1);
        }
        return start;
    }
}
