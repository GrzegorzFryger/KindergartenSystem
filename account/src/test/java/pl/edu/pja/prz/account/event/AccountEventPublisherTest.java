package pl.edu.pja.prz.account.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountEventPublisherTest {
	@Mock
	private ApplicationEventPublisher applicationEventPublisher;
	private AccountEventPublisher accountEventPublisher;

	@BeforeEach
	void setUp() {
		this.accountEventPublisher = new AccountEventPublisher(applicationEventPublisher);
	}

	@Test
	void Should_publishEvent() {
		//given
		var guardianId = UUID.randomUUID();
		var childId = UUID.randomUUID();

		var fullName = new FullName("TestName", "TestSurname");
		var dependency = new GuardianChildDependency(childId,guardianId,fullName);

		//when
		accountEventPublisher.appendChildToGuardianEvent(dependency);

		//then
		verify(applicationEventPublisher, times(1)).publishEvent(any());
	}
}