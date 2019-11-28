package pl.edu.pja.prz.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = {KindergartenSystem.class})
class KindergartenSystemIntegrationTest {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {
	}


	@Test
	void ShouldLoadAccountModule() {
		assertTrue(Arrays.asList(
				applicationContext.getBeanDefinitionNames())
				.contains("pl.edu.pja.prz.account.AccountConfiguration")
		);
	}
}