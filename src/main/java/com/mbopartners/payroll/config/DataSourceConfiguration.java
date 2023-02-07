package com.mbopartners.payroll.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "springDataSourceProperties")
    @ConfigurationProperties("spring.datasource")
    @Primary
    public DataSourceProperties springDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "legacyDataSourceProperties")
    @ConfigurationProperties("legacy.datasource")
    public DataSourceProperties legacyDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource springDataSource(@Qualifier("springDataSourceProperties") DataSourceProperties properties) {
        return DataSourceBuilder.create().driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .password(properties.getPassword())
                .username(properties.getUsername())
                .build();
    }

    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("legacyDataSourceProperties") DataSourceProperties properties) {
        return DataSourceBuilder.create().driverClassName(properties.getDriverClassName())
                .url(properties.getUrl())
                .password(properties.getPassword())
                .username(properties.getUsername())
                .build();
    }
}
