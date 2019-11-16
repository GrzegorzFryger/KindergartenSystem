package pl.edu.pja.prz.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pja.prz.account.domain.PasswordMenager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {AccountConfiguration.class})
class AccountConfigurationIntegrationTest {

	@Autowired
	PasswordMenager passwordMenager;

	@Test
	public void ShouldLoadAnyBeanFromModuleContext() {
		assertNotNull(passwordMenager);
	}

}