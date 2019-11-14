package pl.edu.pja.prz.user;

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
import pl.edu.pja.prz.user.domain.UserAggregate;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.user.repository",
		entityManagerFactoryRef = "userModuleEntityManagerFactory",
		transactionManagerRef = "userModuleTransactionManager")
public class UserConfiguration {

	@Primary
	@Bean
	@ConfigurationProperties("app.datasource.user-module")
	public DataSourceProperties userDataModuleSourceProperties() {
		return new DataSourceProperties();
	}

	@Primary
	@Bean
	@ConfigurationProperties("app.datasource.user-module.configuration")
	public DataSource userModuleDataSource() {
		return userDataModuleSourceProperties().initializeDataSourceBuilder().build();
	}

	@Primary
	@Bean("userModuleEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createUserModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(userModuleDataSource()).packages(UserAggregate.class).build();
	}

	@Primary
	@Bean
	public PlatformTransactionManager userModuleTransactionManager(
			final @Qualifier("userModuleEntityManagerFactory")
					LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}

}
