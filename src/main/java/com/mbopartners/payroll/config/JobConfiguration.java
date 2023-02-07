package com.mbopartners.payroll.config;

import com.mbopartners.payroll.dto.PayRecord;
import com.mbopartners.payroll.dto.PayRecordSummary;
import com.mbopartners.payroll.entity.PayrollDetail;
import com.mbopartners.payroll.mapper.PayrollDetailRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JobConfiguration {

    //@Value("${payroll.date}")
    String payrollDate = "2023-02-02";

    @Order(1)
    @Bean(name = "extractPayrollJob")
    public Job extractPayrollJob(JobBuilderFactory jobBuilderFactory, Step loadPayRecords, Step createPayRecordSummary, Step loadPayrollDetails) {
        return jobBuilderFactory.get("extractPayrollJob")
                .incrementer(new RunIdIncrementer())
                .start(loadPayRecords)
                .next(createPayRecordSummary)
                .next(loadPayrollDetails)
                .build();
    }

    @Bean
    public Step loadPayRecords(ItemReader<PayRecord> reader, ItemProcessor<PayRecord, PayRecord> processor, ItemWriter<PayRecord> writer, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("loadPayRecordsStep")
                .<PayRecord, PayRecord>chunk(1000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(DataIntegrityViolationException.class)
                .skipLimit(1)
                .build();
    }

    @Bean
    public ItemReader<PayRecord> payRecordTransactionItemReader(DataSource dataSource, @Value("${payrecord.read.sql}") String sql) {
        BeanPropertyRowMapper<PayRecord> rowMapper = new BeanPropertyRowMapper<>(PayRecord.class);
        rowMapper.setConversionService(new DefaultConversionService());
        return new JdbcCursorItemReaderBuilder<PayRecord>()
                .name("payRecordReader")
                .sql(sql)
                .queryArguments(payrollDate)
                .dataSource(dataSource)
                .rowMapper(rowMapper)
                .build();
    }

    @Bean
    public ItemWriter<PayRecord> payRecordItemWriter(DataSource dataSource, @Value("${payrecord.write.sql}") String sql) {
        return new JdbcBatchItemWriterBuilder<PayRecord>()
                .beanMapped()
                .dataSource(dataSource)
                .sql(sql)
                .build();
    }

    @Bean
    public Step createPayRecordSummary(ItemReader<PayRecordSummary> reader, ItemWriter<PayRecordSummary> writer, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("createPayrecordSummaryStep")
                .<PayRecordSummary, PayRecordSummary>chunk(1000)
                .reader(reader)
                .writer(writer)
                .faultTolerant()
                .skip(DataIntegrityViolationException.class)
                .skipLimit(1)
                .build();
    }

    @Bean
    public ItemReader<PayRecordSummary> payRecordItemReader(DataSource dataSource, @Value("${payrecord.summary.read.sql}") String sql) {
        BeanPropertyRowMapper<PayRecordSummary> rowMapper = new BeanPropertyRowMapper<>(PayRecordSummary.class);
        rowMapper.setConversionService(new DefaultConversionService());
        return new JdbcCursorItemReaderBuilder<PayRecordSummary>()
                .name("payRecordItemReader")
                .sql(sql)
                .queryArguments(payrollDate)
                .dataSource(dataSource)
                .rowMapper(rowMapper)
                .build();
    }

    @Bean
    public ItemWriter<PayRecordSummary> payRecordSummaryItemWriter(DataSource dataSource, @Value("${payrecord.summary.write.sql}") String sql) {
        return new JdbcBatchItemWriterBuilder<PayRecordSummary>()
                .beanMapped()
                .dataSource(dataSource)
                .sql(sql)
                .build();
    }

    @Bean
    public Step loadPayrollDetails(ItemReader<PayrollDetail> reader, ItemProcessor<PayrollDetail, PayrollDetail> processor, ItemWriter<PayrollDetail> writer, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("loadPayrollDetails")
                .<PayrollDetail, PayrollDetail>chunk(1000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(DataIntegrityViolationException.class)
                .skipLimit(1)
                .build();
    }

    @Bean
    public ItemReader<PayrollDetail> payrollDetailItemReader(DataSource dataSource, @Value("${payroll.detail.read.sql}") String sql) {
        return new JdbcCursorItemReaderBuilder<PayrollDetail>()
                .name("payrollDetailItemReader")
                .sql(sql)
                .queryArguments(payrollDate)
                .dataSource(dataSource)
                .rowMapper(new PayrollDetailRowMapper())
                .build();
    }

    @Bean
    public ItemWriter<PayrollDetail> payrollDetailItemWriter(DataSource dataSource, @Value("${payroll.detail.write.sql}") String sql) {
        return new JdbcBatchItemWriterBuilder<PayrollDetail>()
                .beanMapped()
                .dataSource(dataSource)
                .sql(sql)
                .build();
    }

    /*
    @Order(2)
    @Bean(name = "createPayrollFileJob")
    public Job createPayrollFileJob(JobBuilderFactory jobBuilderFactory, Step createFile) {
        return jobBuilderFactory.get("createPayrollFileJob")
                .incrementer(new RunIdIncrementer())
                .start(createFile)
                .build();
    }

    @Bean
    public Step createFile(ItemReader<String> reader, ItemProcessor<String, List<AdpPayrollLineItem>> processor, ItemWriter<List<AdpPayrollLineItem>> writer, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("createFile")
                .<String, List<AdpPayrollLineItem>>chunk(100)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .skip(DataIntegrityViolationException.class)
                .skipLimit(1)
                .build();
    }

    @Bean
    public ItemReader<String> payRecordSummaryItemReader(PayRecordSummaryRepository repository, @Value("${payroll.job.id}") Long payrollJobId) {
        return new RepositoryItemReaderBuilder<String>()
                .name("payRecordSummaryReader")
                .repository(repository)
                .methodName("findDistinctMemberNumber")
                .arguments(payrollJobId)
                .pageSize(1000)
                .sorts(new HashMap<>())
                .build();
    }
*/

}
