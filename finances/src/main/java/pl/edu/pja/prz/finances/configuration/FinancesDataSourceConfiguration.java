package pl.edu.pja.prz.finances.configuration;

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
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.finances.repository",
        entityManagerFactoryRef = "financesModuleEntityManagerFactory",
        transactionManagerRef = "financesModuleTransactionManager")
public class FinancesDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.finances-module")
    public DataSourceProperties financesDataModuleSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.finances-module.configuration")
    public DataSource financesModuleDataSource() {
        return financesDataModuleSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("financesModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean createReceivablesModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(financesModuleDataSource()).build();
    }

    @Bean
    public PlatformTransactionManager financesModuleTransactionManager(
            final @Qualifier("financesModuleEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }

}
