package com.bryan.springbootwas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiObjectFactoryBean;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
     protected ApplicationProperties applicationProperties;

    @Bean
    @Primary //solo un bean puede ser primary
    public DataSource exampleDataSource() throws NamingException {
        JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
        jndi.setExpectedType(DataSource.class);
        jndi.setJndiName(applicationProperties.getExampleCuentaSueldo());
        jndi.afterPropertiesSet();
        return (DataSource) jndi.getObject();
    }

    @Bean
    public JdbcTemplate exampleJdbcTemplate() throws NamingException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(exampleDataSource());
        return jdbcTemplate;
    }
}
