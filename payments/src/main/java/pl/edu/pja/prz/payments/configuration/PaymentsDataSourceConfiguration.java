package pl.edu.pja.prz.payments.configuration;

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
import pl.edu.pja.prz.payments.model.Payment;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.payments.repository",
		entityManagerFactoryRef = "paymentsModuleEntityManagerFactory",
		transactionManagerRef = "paymentsModuleTransactionManager")
public class PaymentsDataSourceConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.payments-module")
	public DataSourceProperties paymentsDataModuleSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.payments-module.configuration")
	public DataSource accountModuleDataSource() {
		return paymentsDataModuleSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("paymentsModuleEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createPaymentsModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(accountModuleDataSource()).packages(Payment.class).build();
	}

	@Bean
	public PlatformTransactionManager paymentsModuleTransactionManager(
			final @Qualifier("paymentsModuleEntityManagerFactory")
					LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
