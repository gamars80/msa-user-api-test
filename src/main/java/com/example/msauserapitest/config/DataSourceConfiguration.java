package com.example.msauserapitest.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("!test")
public class DataSourceConfiguration {
    public static final String WRITE_DATA_SOURCE = "writeDataSource";
    public static final String READ_DATA_SOURCE = "readDataSource";

    @Bean(name = WRITE_DATA_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.write.hikari")
    public DataSource getWriteDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = READ_DATA_SOURCE)
    @ConfigurationProperties(prefix = "spring.datasource.read.hikari")
    public DataSource getReadDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
