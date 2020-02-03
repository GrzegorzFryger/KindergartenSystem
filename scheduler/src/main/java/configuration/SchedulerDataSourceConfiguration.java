package configuration;

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
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.scheduler",
		entityManagerFactoryRef = "schedulerModuleEntityManagerFactory",
		transactionManagerRef = "schedulerModuleTransactionManager")
public class SchedulerDataSourceConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.scheduler-module")
	public DataSourceProperties schedulerDataModuleSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.scheduler-module.configuration")
	public DataSource schedulerModuleDataSource() {
		return schedulerDataModuleSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("schedulerModuleEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createSchedulerModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(schedulerModuleDataSource()).packages(SchedulerDataSourceConfiguration.class).build();
	}

	@Bean
	public PlatformTransactionManager schedulerModuleTransactionManager(
			final @Qualifier("schedulerModuleEntityManagerFactory")
					LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
