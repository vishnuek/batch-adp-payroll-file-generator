package com.mbopartners.payroll.tasklets.processor;

import com.mbopartners.payroll.entity.PayrollDetail;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@StepScope
@Component
public class PayrollDetailProcessor implements ItemProcessor<PayrollDetail, PayrollDetail> {
    private Long jobId;

    public PayrollDetailProcessor(@Value("#{stepExecution.jobExecution.id}") Long jobId) {
        this.jobId = jobId;
    }

    @Override
    public PayrollDetail process(PayrollDetail payrollDetail) {
        payrollDetail.setJobId(jobId);
        return payrollDetail;
    }
}
