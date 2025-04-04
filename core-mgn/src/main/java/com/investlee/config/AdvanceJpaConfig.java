package com.investlee.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(
        basePackages = {"com.investlee.domain.advance",
                "com.investlee.domain.migration"},
        entityManagerFactoryRef = "advanceEntityManagerFactory",
        transactionManagerRef = "advanceTransactionManager"
)
@EnableTransactionManagement
public class AdvanceJpaConfig {

    @Primary
    @Bean("advanceDataSource")
    @ConfigurationProperties(prefix = "spring.jpa.advance-db.hikari")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    @Bean("advanceJpaProperties")
    @ConfigurationProperties(prefix = "spring.jpa.advance-db.properties")
    public Properties jpaProperties() {
        return new Properties();
    }

    @Primary
    @Bean("advanceEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean advanceEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("advanceDataSource") DataSource dataSource,
            @Qualifier("advanceJpaProperties") Properties jpaProperties) {
        LocalContainerEntityManagerFactoryBean factoryBean = builder
                .dataSource(dataSource)
                .packages("com.investlee.domain.advance",
                        "com.investlee.domain.migration")
                .build();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    @Primary
    @Bean("advanceTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("advanceEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}