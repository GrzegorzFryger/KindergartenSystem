package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.BoroughRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoroughServiceTest {
	@Mock
	private BoroughRepository boroughRepository;
	@Mock
	private Borough borough;

	private BoroughServiceImpl boroughService;

	@BeforeEach
	void setUp() {
		boroughService = new BoroughServiceImpl(boroughRepository);
	}

	@Test
	void should_creatBorough() {
		//given
		var borough = new Borough("Test borough",
				new Address("70-700", "City", "Street 256"),
				new Phone("123132123"),
				"test@test.com",
				"99576122623"
		);

		//when
		when(boroughRepository.save(any(Borough.class))).thenReturn(borough);
		var returnedBorough = boroughService.create(borough);

		//then
		assertNotNull(returnedBorough);
		verify(boroughRepository, times(1)).save(borough);
	}

	@Test
	void should_addChildToBorough() {
		//given
		var borough = new Borough("Test borough",
				new Address("70-700", "City", "Street 256"),
				new Phone("123132123"),
				"test@test.com",
				"99576122623"
		);

		var child = new Child(Gender.MALE, borough, "97071105694",
				new FullName("TestName", "TestSurname"),
				new Age(LocalDate.now()),
				new Address("70-700", "City", "Street 256"),
				new StudyPeriod(LocalDate.MIN, LocalDate.MAX)

		);

		//when
		boroughService.addChildToBorough(child, this.borough);

		//then

		verify(this.borough, times(1)).addChild(any(Child.class));
		verify(boroughRepository, times(1)).save(this.borough);
	}

	@Test
	void should_findBorough() {
		//given
		var id = Long.valueOf(1);

		var borough = new Borough("Test borough",
				new Address("70-700", "City", "Street 256"),
				new Phone("123132123"),
				"test@test.com",
				"99576122623"
		);

		//when
		when(boroughRepository.findById(1L)).thenReturn(Optional.of(borough));
		var returnedValue = boroughService.find(1L);

		//then
		verify(boroughRepository, times(1)).findById(1L);
	}
}