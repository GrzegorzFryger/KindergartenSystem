package pl.edu.pja.prz.groups.configuration;

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
import pl.edu.pja.prz.groups.model.Group;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "pl.edu.pja.prz.groups.repository",
        entityManagerFactoryRef = "groupsModuleEntityManagerFactory",
        transactionManagerRef = "groupsModuleTransactionManager")
public class GroupsDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.groups-module")
    public DataSourceProperties groupsDataModuleSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.groups-module.configuration")
    public DataSource groupsModuleDataSource() {
        return groupsDataModuleSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean("groupsModuleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean createGroupsModuleEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(groupsModuleDataSource()).packages(Group.class).build();
    }

    @Bean
    public PlatformTransactionManager groupsModuleTransactionManager(
            final @Qualifier("groupsModuleEntityManagerFactory")
                    LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean) {
        return new JpaTransactionManager(containerEntityManagerFactoryBean.getObject());
    }
}