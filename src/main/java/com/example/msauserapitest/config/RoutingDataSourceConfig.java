package com.example.msauserapitest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.example.msauserapitest.config.DataSourceConfiguration.READ_DATA_SOURCE;
import static com.example.msauserapitest.config.DataSourceConfiguration.WRITE_DATA_SOURCE;

@Slf4j
@EnableJpaRepositories(
        basePackages = "com.example.msauserapitest"
)
@Configuration
@Profile("!test")
public class RoutingDataSourceConfig {
    private final String ROUTING_DATA_SOURCE = "routingDataSource";
    private final String DATA_SOURCE = "dataSource";

    @Bean(ROUTING_DATA_SOURCE)
    public DataSource routingDataSource(
            @Qualifier(WRITE_DATA_SOURCE) final DataSource writeDataSource,
            @Qualifier(READ_DATA_SOURCE) final DataSource readDataSource
    ) {
        RoutingDataSource routingDataSource = new RoutingDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.WRITE, writeDataSource);
        dataSourceMap.put(DataSourceType.READ, readDataSource);

        routingDataSource.setTargetDataSources(dataSourceMap);
        routingDataSource.setDefaultTargetDataSource(writeDataSource);

        return routingDataSource;
    }

    @Bean(DATA_SOURCE)
    public DataSource dataSource(@Qualifier(ROUTING_DATA_SOURCE) DataSource routingDataSource) {
        return new LazyConnectionDataSourceProxy(routingDataSource);
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(DATA_SOURCE) DataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setPackagesToScan("com.example.msauserapitest");
        entityManagerFactory.setJpaVendorAdapter(this.jpaVendorAdapter());
        entityManagerFactory.setPersistenceUnitName("entityManager");

        Properties jpaProperties = new Properties();
//        jpaProperties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        // Hibernate 6.x와 호환되는 PhysicalNamingStrategy 설정
        jpaProperties.put("hibernate.physical_naming_strategy",
                "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
//        jpaProperties.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
        entityManagerFactory.setJpaProperties(jpaProperties);

        return entityManagerFactory;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setGenerateDdl(false);
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect");

        return hibernateJpaVendorAdapter;
    }

    @Bean("transactionManager")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

        return jpaTransactionManager;
    }
}
