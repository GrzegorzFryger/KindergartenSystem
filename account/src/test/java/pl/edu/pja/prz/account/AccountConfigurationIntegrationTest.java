package pl.edu.pja.prz.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.edu.pja.prz.account.service.PasswordManager;
import pl.edu.pja.prz.account.service.PasswordManagerImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = {AccountConfiguration.class, PasswordManagerImpl.class})
class AccountConfigurationIntegrationTest {

	@Autowired
	PasswordManager passwordManager;

	@Test
	public void ShouldLoadAnyBeanFromModuleContext() {
		assertNotNull(passwordManager);
	}


}