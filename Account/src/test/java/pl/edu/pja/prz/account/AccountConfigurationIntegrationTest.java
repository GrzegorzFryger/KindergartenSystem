package pl.edu.pja.prz.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pja.prz.account.domain.entity.Account;
import pl.edu.pja.prz.account.infrastructure.PasswordManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {AccountConfiguration.class})
class AccountConfigurationIntegrationTest {

	@Autowired
	PasswordManager passwordManager;

	@Test
	public void ShouldLoadAnyBeanFromModuleContext() {
		assertNotNull(passwordManager);
	}

	@Test
	public void ShouldLoadAnyBoduleContext() {
		Account account = new Account();
	}

}