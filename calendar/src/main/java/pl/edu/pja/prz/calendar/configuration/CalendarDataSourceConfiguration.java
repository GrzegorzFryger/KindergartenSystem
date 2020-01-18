package pl.edu.pja.prz.receivables.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.calendar.repository",
        entityManagerFactoryRef = "calendarModuleEntityManagerFactory",
        transactionManagerRef = "calendarModuleTransactionManager")
public class CalendarDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.calendar-module")
    public DataSourceProperties calendarDataModuleSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.calendar-module.configuration")
    public DataSource calendarModuleDataSource() {
        return calendarDataModuleSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("calendarModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean createCalendarModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(calendarModuleDataSource()).packages("pl.edu.pja.prz.calendar.model").build();
    }

    @Bean
    public PlatformTransactionManager calendarModuleTransactionManager(
            final @Qualifier("calendarModuleEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }

}
