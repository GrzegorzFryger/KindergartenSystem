package pl.edu.pja.prz.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
		// There add your .properties file from module:
		// classpath:application-nameModule.properties
		@PropertySource("classpath:application-account.properties")
})
public class KindergartenSystemConfiguration {
}
