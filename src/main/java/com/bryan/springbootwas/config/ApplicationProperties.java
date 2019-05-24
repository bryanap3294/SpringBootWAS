package com.bryan.springbootwas.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource("classpath:application_${env}.properties")
})
public class ApplicationProperties {

    @Value("${example.datasource}")
    private String exampleCuentaSueldo;

    public String getExampleCuentaSueldo() {
        return exampleCuentaSueldo;
    }

    public void setExampleCuentaSueldo(String exampleCuentaSueldo) {
        this.exampleCuentaSueldo = exampleCuentaSueldo;
    }
}
