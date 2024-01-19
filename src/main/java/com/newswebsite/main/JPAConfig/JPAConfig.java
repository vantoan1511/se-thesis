package com.newswebsite.main.JPAConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories(basePackages = {"com.newswebsite.main.repository"})
@EnableTransactionManagement
public class JPAConfig {

    @Value("${datasource.driver}")
    private String dataSourceDriver;
    @Value("${datasource.url}")
    private String dataSourceUrl;
    @Value("${datasource.username}")
    private String dataSourceUsername;
    @Value("${datasource.password}")
    private String dataSourcePassword;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource()); // Set the data source bean
        em.setPersistenceUnitName("persistence-data");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Additional configuration options can be set here
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        //properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        //properties.setProperty("hibernate.hbm2ddl.auto", "create");
        //properties.setProperty("hibernate.hbm2ddl.auto", "none");
        properties.setProperty("hibernate.hbm2ddl.auto", hbm2ddl);
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

}
