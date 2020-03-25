package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.event.AccountEventPublisher;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.ChildBuilder;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GuardianServiceTest {
	private GuardianService guardianService;

	@Mock
	private GuardianRepository guardianRepository;
	@Mock
	private AccountFactory accountFactory;
	@Mock
	private PasswordManager passwordManager;
	@Mock
	private RoleService roleService;
	@Mock
	private ChildService childService;
	@Mock
	private AccountEventPublisher accountEventPublisher;

	// data
	private Address address;
	private Phone phone;
	private FullName fullName;
	private Password password;
	private String email;
	private AccountStatus status;


	@BeforeEach
	void setUp() {
		this.guardianService = new GuardianService(
				guardianRepository,
				accountFactory,
				passwordManager,
				roleService,
				childService,
				accountEventPublisher
		);

		address = new Address("70-700", "City", "Street 256");
		phone = new Phone("123132123");
		fullName = new FullName("TestName", "TestSurname");
		password = new Password("newPassword");
		email = "test@test.com";
		status = AccountStatus.ACTIVE;
	}

//	@Test
//	void createGuardianAccount() {
//		//given
//		var guardian = new AdministratorAccountFactoryImpl().createGuardian(new Person(address, fullName, phone), password, email);
//
//		//when
//		when(passwordManager.generateEncodeRandomPassword()).thenReturn(password.getPassword());
//		when(administratorAccountFactory.createStandardAccount(
//				any(Person.class), any(Password.class), any(), any()
//		)).thenReturn(guardian);
//		when(guardianRepository.save(any(Guardian.class))).thenReturn(guardian);
//		doNothing().when(roleService).persistRoleFromUser(any(Guardian.class));
//		var createdGuardian = administratorService.createGuardianAccount(address, fullName, phone, email);
//
//		//then
//		assertEquals(guardian, createdGuardian);
//		verify(passwordManager, times(1)).generateEncodeRandomPassword();
//		verify(administratorAccountFactory, times(1))
//				.createStandardAccount(
//						any(Person.class), any(Password.class), any(), any()
//				);
//		verify(guardianRepository, times(1)).save(any());
//		verify(roleService, times(1)).persistRoleFromUser(any());
//
//	}


	@Test
	public void Should_PublishEvent_When_GuardianWasCreated() {
		//given
		var childId = UUID.randomUUID();
		var guardianId = UUID.randomUUID();

		var child = ChildBuilder.aChild()
				.withPeselNumber("00440758725")
				.withFullName(fullName)
				.withAddress(address)
				.withGuardians(new HashSet<>())
				.build();

		var guardian = new Guardian();
		guardian.setFullName(fullName);
		guardian.setAddress(address);
		guardian.setPhoneNumber(phone);
		guardian.setEmail(email);
		guardian.setAccountStatus(status);

		child.setId(childId);
		guardian.setId(guardianId);

		var dependency = new GuardianChildDependency(guardian.getId(), child.getId(), guardian.getFullName());

		//when
		when(childService.getById(childId)).thenReturn(child);
		when(guardianRepository.findById(guardianId)).thenReturn(Optional.ofNullable(guardian));
		when(guardianRepository.save(guardian)).thenReturn(guardian);

		this.guardianService.appendChildrenToGuardian(childId, guardianId);

		//then
		verify(accountEventPublisher, times(1)).appendChildToGuardianEvent(any());
		verify(accountEventPublisher).appendChildToGuardianEvent(argThat((arg) ->
				arg.equals(dependency)
		));
	}
}

