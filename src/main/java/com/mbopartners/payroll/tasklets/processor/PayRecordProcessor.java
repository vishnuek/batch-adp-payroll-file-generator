package com.mbopartners.payroll.tasklets.processor;

import com.mbopartners.payroll.dto.PayRecord;
import com.mbopartners.payroll.dto.TimePeriod;
import com.mbopartners.payroll.tasklets.TimePeriodDescriptionParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@StepScope
@Component
public class PayRecordProcessor implements ItemProcessor<PayRecord, PayRecord> {

    private TimePeriodDescriptionParser parser;
    private Long jobId;

    public PayRecordProcessor(TimePeriodDescriptionParser parser, @Value("#{stepExecution.jobExecution.id}") Long jobId) {
        this.parser = parser;
        this.jobId = jobId;
    }

    @Override
    public PayRecord process(PayRecord payRecord) {
        TimePeriod timePeriod = parser.parse(payRecord);
        if (timePeriod == null) {
            //let's make it last friday vs. skipping it
            log.error("Skipping pay record because of empty time period: {}", payRecord);
            return null;
        }
        payRecord.setTimePeriodStartDate(timePeriod.getStartDate());
        payRecord.setTimePeriodEndDate(timePeriod.getEndDate());
        payRecord.setJobId(jobId);
        return payRecord;
    }
}
