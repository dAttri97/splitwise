package com.attri.splitwise.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class DBConfig {

    @Value("${spring.datasource.url:jdbc:mysql://database-1.cduoywg22ojt.ap-south-1.rds.amazonaws.com:3306/splitwise}")
    private String jdbcUrl;

    @Value("${spring.datasource.username:admin}")
    private String username;

    @Value("${spring.datasource.password:rdstest12345}")
    private String password;

    @Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
    private String driverClassName;

    @Bean("primaryDataSource")
    public DataSource primaryDataSource() {
        HikariConfig hc = new HikariConfig();
        hc.setDriverClassName("com.mysql.cj.jdbc.Driver");
        // Use vault configuration
        hc.setJdbcUrl(jdbcUrl);
        hc.setUsername(username);
        hc.setPassword(password);
        hc.setMaximumPoolSize(5);
        hc.setMinimumIdle(500);
        hc.setIdleTimeout(500);
        hc.setConnectionTimeout(2000);
        hc.setLeakDetectionThreshold(60000);
        hc.addDataSourceProperty("dataSource.cachePrepStmts", "true");
        hc.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
        hc.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
        hc.addDataSourceProperty("dataSource.useServerPrepStmts", "true");

        return new HikariDataSource(hc);
    }

//    @Bean
//    public DataSource readDataSource() {
//        HikariConfig hc = new HikariConfig();
//        hc.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        hc.setJdbcUrl(jdbcUrl);
//        hc.setUsername(username);
//        hc.setPassword(password);
//        hc.setMaximumPoolSize(5);
//        hc.setMinimumIdle(500);
//        hc.setIdleTimeout(500);
//        hc.setConnectionTimeout(2000);
//        hc.setLeakDetectionThreshold(60000);
//        hc.setConnectionTimeout(2000);
//        hc.addDataSourceProperty("dataSource.cachePrepStmts", "true");
//        hc.addDataSourceProperty("dataSource.prepStmtCacheSize", "250");
//        hc.addDataSourceProperty("dataSource.prepStmtCacheSqlLimit", "2048");
//        hc.addDataSourceProperty("dataSource.useServerPrepStmts", "true");
//
//        return new HikariDataSource(hc);
//    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(false);
        Map<String, String> properties = new HashMap<>();
        properties.put("hibernate.implicit_naming_strategy", "org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl");
        properties.put("hibernate.physical_naming_strategy", "org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
        properties.put("hibernate.show_sql", String.valueOf(false));
        properties.put("hibernate.listeners.envers.autoRegister", "true");
        properties.put("hibernate.envers.autoRegisterListeners", "true");
        //properties.put("hibernate.hbm2ddl.auto", "update");
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(primaryDataSource());
        factoryBean.setPackagesToScan("com.attri.*");
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        factoryBean.getJpaPropertyMap().putAll(properties);
        return factoryBean;
    }
}
