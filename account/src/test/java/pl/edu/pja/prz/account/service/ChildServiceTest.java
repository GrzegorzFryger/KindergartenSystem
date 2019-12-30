package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.ChildBuilder;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.account.repository.ChildRepository;
import pl.edu.pja.prz.account.utilites.PeselService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ChildServiceTest {
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;

	@Mock
	private BoroughService boroughService;
	@Mock
	private ChildRepository childRepository;
	@Mock
	private PeselService peselService;
	private ChildService childService;

	@BeforeEach
	void setUp() {
		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";

		childService = new ChildServiceImpl(childRepository,peselService,boroughService);
	}

	@Test
	void createChild() {
		//given
		var borough = new Borough("Testborought", address, phone, "test@wp.com", "975456466");
		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withBorough(borough)
				.build();

		//when
		when(boroughService.find(any())).thenReturn(Optional.of(borough));
		when(childService.createChild(any(), any(), any(), any(), any())).thenReturn(child);
		var createdChild = childService.createChild(1L, address, fullName, "00440758725", new StudyPeriod());

		//then
		assertEquals(child, createdChild);
		verify( boroughService, times(1)).find(any(Long.class));
//		verify(childService, times(1)).createChild(any(Address.class), any(Borough.class),
//				any(FullName.class), any(String.class), any(StudyPeriod.class)
//		);
		verify( boroughService, times(1)).addChildToBorough(any(Child.class),any(Borough.class));
	}

	@Test
	void createChild1() {
	}

	@Test
	void getChildById() {
	}
}