package pl.edu.pja.prz.meal.configuration;

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
import pl.edu.pja.prz.meal.model.Meal;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.meal.repository",
		entityManagerFactoryRef = "mealModuleEntityManagerFactory",
		transactionManagerRef = "mealModuleTransactionManager")
public class MealDataSourceConfiguration {

	@Bean
	@ConfigurationProperties("app.datasource.meal-module")
	public DataSourceProperties mealDataModuleSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties("app.datasource.meal-module.configuration")
	public DataSource mealModuleDataSource() {
		return mealDataModuleSourceProperties().initializeDataSourceBuilder().build();
	}

	@Bean("mealModuleEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean createMealModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mealModuleDataSource()).packages(Meal.class).build();
	}

	@Bean
	public PlatformTransactionManager mealModuleTransactionManager(
			final @Qualifier("mealModuleEntityManagerFactory")
					LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
		return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
	}
}
