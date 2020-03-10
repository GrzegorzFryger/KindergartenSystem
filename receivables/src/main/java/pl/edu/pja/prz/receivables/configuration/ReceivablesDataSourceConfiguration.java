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
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.receivables.repository",
        entityManagerFactoryRef = "receivablesModuleEntityManagerFactory",
        transactionManagerRef = "receivablesModuleTransactionManager")
public class ReceivablesDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.receivables-module")
    public DataSourceProperties receivablesDataModuleSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.receivables-module.configuration")
    public DataSource receivablesModuleDataSource() {
        return receivablesDataModuleSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("receivablesModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean createReceivablesModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(receivablesModuleDataSource()).packages("pl.edu.pja.prz.receivables.model").build();
    }

    @Bean
    public PlatformTransactionManager receivablesModuleTransactionManager(
            final @Qualifier("receivablesModuleEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }

}
