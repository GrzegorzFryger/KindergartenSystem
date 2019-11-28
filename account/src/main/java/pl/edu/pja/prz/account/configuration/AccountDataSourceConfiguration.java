package pl.edu.pja.prz.account.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.edu.pja.prz.account.model.entity.Account;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.account.model.repository",
		entityManagerFactoryRef = "accountModuleEntityManagerFactory",
		transactionManagerRef = "accountModuleTransactionManager")
public class AccountDataSourceConfiguration {


	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.account-module")
	public DataSourceProperties accountDataModuleSourceProperties() {
		return new DataSourceProperties();
	}


	@Bean
	@Primary
	@ConfigurationProperties("app.datasource.account-module.configuration")
	public DataSource accountModuleDataSource() {
		return accountDataModuleSourceProperties().initializeDataSourceBuilder().build();
	}


	@Bean("accountModuleEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean createAccountModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(accountModuleDataSource()).packages(Account.class).build();
	}


	@Bean
	@Primary
	public PlatformTransactionManager accountModuleTransactionManager(
			final @Qualifier("accountModuleEntityManagerFactory")
					LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
