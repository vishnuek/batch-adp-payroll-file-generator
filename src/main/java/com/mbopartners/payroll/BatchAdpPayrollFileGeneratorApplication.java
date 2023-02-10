package com.mbopartners.payroll;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@PropertySource({"classpath:sftp.properties"})
@SpringBootApplication
@EnableBatchProcessing
public class BatchAdpPayrollFileGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchAdpPayrollFileGeneratorApplication.class, args);
	}

}
