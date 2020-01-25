package pl.edu.pja.prz.mail.configuration;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.mail-module")
    public DataSourceProperties mailDataModuleSourceProperties() {
        return new DataSourceProperties();
    }
}