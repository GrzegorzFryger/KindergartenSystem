package pl.edu.pja.prz.account.utilities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.pja.prz.account.utilites.PasswordManagerImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PasswordManagerImplTest {

	@Mock
  	private PasswordEncoder passwordEncoder;
	private PasswordManagerImpl passwordManager;

	@BeforeEach
	void setUp() {
		this.passwordManager = new PasswordManagerImpl(passwordEncoder);
	}

	@Test
	void shouldEncodeRawPassword_When_callMethodEncodeInPasswordManager() {
		//given
		var rawPassword = "password";
		var encodedPassword = "$2a$10$9RqeWXQBU0OTZdtc2DPs8OtEWF0pH34/Zk3Wn5PraWPjXhx/WKvM2";

		//when
		when(passwordEncoder.encode(any())).thenAnswer(invocationOnMock -> encodedPassword);

		var result = passwordManager.encode(rawPassword);

		assertEquals(encodedPassword,result);
	}

	@Test
	void shouldGeneratePasswordLengthThan6_When_PasswordManagerGenerateRandomPassword() {
		//given

		//when
		passwordManager.generateEncodeRandomPassword();

		//then
		verify(passwordEncoder).encode(argThat((arg) -> arg.length() > 6));
	}

	@Test
	void shouldCallPasswordEncoderEncodeMethodWithTheSameParameters_When_InvokeEncodeMethodInPasswordManager() {
		//given
		List<Object> arg = new ArrayList<>();
		var rawPassword = "password";
		var encodePassword = "$2a$10$9RqeWXQBU0OTZdtc2DPs8OtEWF0pH34/Zk3Wn5PraWPjXhx/WKvM2";

		//when
		when(passwordEncoder.matches(any(),any())).thenAnswer(inv -> {
			arg.add(inv.getArguments()[0]);
			arg.add(inv.getArguments()[1]);
			return true;
		});

		var result = passwordManager.matches(rawPassword,encodePassword);

		//then
		assertEquals(rawPassword,arg.get(0));
		assertEquals(encodePassword,arg.get(1));
		assertTrue(result);


	}




}