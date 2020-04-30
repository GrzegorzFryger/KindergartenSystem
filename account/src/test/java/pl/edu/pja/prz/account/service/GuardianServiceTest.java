package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.event.AccountEventPublisher;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.repository.GuardianRepository;
import pl.edu.pja.prz.account.utilites.AccountFactory;
import pl.edu.pja.prz.account.utilites.ChildBuilder;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;
import pl.edu.pja.prz.commons.model.Phone;
import pl.edu.pja.prz.mail.facade.MailFacade;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    MailFacade mailFacade;
    @Mock
    ActivateTokenService activateTokenService;

    // data
    private Address address;
    private Phone phone;
    private FullName fullName;
    private Password password;
    private String email;
    private AccountStatus status;
    private Account account;


    @BeforeEach
    void setUp() {
        this.guardianService = new GuardianService(
                guardianRepository,
                accountFactory,
                passwordManager,
                roleService,
                childService,
                accountEventPublisher,
                mailFacade,
                activateTokenService
        );

        address = new Address("70-700", "City", "Street 256");
        fullName = new FullName("TestName", "TestSurname");
        phone = new Phone("123132123");
        password = new Password("newPassword");
        status = AccountStatus.ACTIVE;
        email = "test@test.com";

        account = new Account();

        account.setAddress(address);
        account.setFullName(fullName);
        account.setPhoneNumber(phone);
        account.setPassword(password);
        account.setAccountStatus(status);
        account.setId(UUID.randomUUID());
        account.setEmail(email);
    }

    @Test
    public void Should_CreateGuardianAccount() {
        //given
        var guardian = new Guardian();
        guardian.setFullName(fullName);
        guardian.setAddress(address);
        guardian.setPhoneNumber(phone);
        guardian.setEmail(email);
        guardian.setAccountStatus(status);

        Person person = guardian;

        //when
        when(guardianRepository.findByEmailAndFullName(guardian.getEmail(), guardian.getFullName()))
                .thenReturn(Optional.empty());
        when(accountFactory.createStandardAccount(any(Person.class), any(), any(), any()))
                .thenReturn(guardian);
        when(guardianRepository.save(any(Guardian.class)))
                .thenReturn(guardian);
        doNothing().when(roleService).persistRoleFromUser(guardian);

        guardianService.createGuardianAccount(person, guardian.getEmail());

        //then
        verify(guardianRepository, times(1))
                .findByEmailAndFullName(
                        argThat((arg) -> arg.equals(guardian.getEmail())),
                        argThat((arg) -> arg.equals(guardian.getFullName()))
                );
        verify(accountFactory, times(1))
                .createStandardAccount(
                        argThat((arg) -> arg.equals(person)),
                        any(Password.class),
                        argThat((arg) -> arg.equals(guardian.getEmail())),
                        argThat((arg) -> arg.equals(Guardian.class))
                );
        verify(roleService, times(1))
                .persistRoleFromUser(
                        argThat((arg) -> arg.equals(guardian))
                );
    }

    @Test
    void Should_AppendChildrenToGuardian() {
        //given
        var childId = UUID.randomUUID();
        var guardianId = UUID.randomUUID();

        var child = new Child();

        child.setFullName(new FullName("TestName", "TestSurname"));
        child.setAddress(new Address("70-700", "City", "Street 256"));
        child.setAge(new Age(LocalDate.of(2008, Month.FEBRUARY, 15)));
        child.setGender(Gender.MALE);
        child.setPeselNumber("00440758725");
        child.setStudyPeriod(
                new StudyPeriod(
                        LocalDate.of(2014, Month.SEPTEMBER, 01),
                        LocalDate.of(2015, Month.JULY, 26))
        );

        var guardian = new Guardian();
        var guardianToTest = new Guardian();

        guardian.setFullName(fullName);
        guardian.setAddress(address);
        guardian.setPhoneNumber(phone);
        guardian.setEmail(email);
        guardian.setAccountStatus(status);

        guardianToTest.setFullName(fullName);
        guardianToTest.setAddress(address);
        guardianToTest.setPhoneNumber(phone);
        guardianToTest.setEmail(email);
        guardianToTest.setAccountStatus(status);

        child.setId(childId);
        guardian.setId(guardianId);
        guardianToTest.setId(guardianId);

        //when
        when(childService.getById(childId)).thenReturn(child);
        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(guardianToTest));
        when(guardianRepository.save(any(Guardian.class))).thenReturn(guardianToTest);
        doNothing().when(accountEventPublisher).appendChildToGuardianEvent(any(GuardianChildDependency.class));

        this.guardianService.appendChildrenToGuardian(childId, guardianId);

        //then
        verify(guardianRepository, times(1))
                .findById(argThat((arg) -> arg.equals(guardianId)));

        verify(guardianRepository).save(argThat((arg) -> {
            guardian.setChildren(Set.of(child));
            return arg.equals(guardian);
        }));

        verify(accountEventPublisher, times(1)).appendChildToGuardianEvent(any());
    }

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

    @Test
    void Should_ThrowElementNotFoundException_When_GuardianNotFound() {
        //given
        var childId = UUID.randomUUID();
        var guardianId = UUID.randomUUID();

        //when

        assertThrows(ElementNotFoundException.class, () -> {
            this.guardianService.appendChildrenToGuardian(childId, guardianId);
        });
    }

    @Test
    void Should_ReturnAllChildren() {
        var guardianId = UUID.randomUUID();

        var child = new Child();
        child.setFullName(new FullName("TestName", "TestSurname"));
        child.setAddress(new Address("70-700", "City", "Street 256"));
        child.setAge(new Age(LocalDate.of(2008, Month.FEBRUARY, 15)));
        child.setGender(Gender.MALE);
        child.setPeselNumber("00440758725");
        child.setStudyPeriod(
                new StudyPeriod(
                        LocalDate.of(2014, Month.SEPTEMBER, 01),
                        LocalDate.of(2015, Month.JULY, 26))
        );
        var childToTest = new Child();
        childToTest.setFullName(new FullName("TestName", "TestSurname"));
        childToTest.setAddress(new Address("70-700", "City", "Street 256"));
        childToTest.setAge(new Age(LocalDate.of(2008, Month.FEBRUARY, 15)));
        childToTest.setGender(Gender.MALE);
        childToTest.setPeselNumber("00440758725");
        childToTest.setStudyPeriod(
                new StudyPeriod(
                        LocalDate.of(2014, Month.SEPTEMBER, 01),
                        LocalDate.of(2015, Month.JULY, 26))
        );

        var guardian = new Guardian();
        guardian.setId(guardianId);
        guardian.setFullName(fullName);
        guardian.setAddress(address);
        guardian.setPhoneNumber(phone);
        guardian.setEmail(email);
        guardian.setAccountStatus(status);
        guardian.setChildren(Set.of(child));

        //when
        when(guardianRepository.findById(guardianId)).thenReturn(Optional.of(guardian));
        var setOfChildren = this.guardianService.getAllChildren(guardianId);

        //then
        verify(guardianRepository, times(1)).findById(
                argThat((arg) -> arg.equals(guardianId))
        );
        assertEquals(Set.of(childToTest), setOfChildren);
    }

    @Test
    void Should_ThrowElementNotFoundException_When_GuardianNotFound_GetAllChildren() {
        //given
        var guardianId = UUID.randomUUID();

        //when
        assertThrows(ElementNotFoundException.class, () -> {
            this.guardianService.getAllChildren(guardianId);
        });
    }


    @Test
    void Should_ThrowMoreThanOneElement_When_FindMoreGuardian() {
        //given
        var guardian = new Guardian();
        var guardianToTest = new Guardian();

        guardian.setFullName(fullName);
        guardian.setAddress(address);
        guardian.setPhoneNumber(phone);
        guardian.setEmail(email);
        guardian.setAccountStatus(status);

        guardianToTest.setFullName(fullName);
        guardianToTest.setAddress(address);
        guardianToTest.setPhoneNumber(phone);
        guardianToTest.setEmail(email);
        guardianToTest.setAccountStatus(status);

        //when
        when(guardianRepository.findReadOnly(any(), any())).thenReturn(List.of(guardian, guardianToTest));

        //then
        assertThrows(MoreThanOneElement.class,
                () -> this.guardianService.findByFullNameOrAddressReadOnly(fullName, null)
        );

        assertThrows(MoreThanOneElement.class,
                () -> this.guardianService.findByFullNameOrAddressReadOnly(fullName, "test street")
        );
    }

    @Test
    void Should_FindByFullNameOrAddress() {
        //given
        var guardianToTest = new Guardian();

        guardianToTest.setFullName(fullName);
        guardianToTest.setAddress(address);

        //when
        when(guardianRepository.findReadOnly(any(), eq(Guardian.class))).thenReturn(List.of(guardianToTest));

        try {
            this.guardianService.findByFullNameOrAddressReadOnly(fullName, null);
            this.guardianService.findByFullNameOrAddressReadOnly(fullName, "test street");
        } catch (MoreThanOneElement moreThanOneElement) {
            moreThanOneElement.printStackTrace();
        }

        //then
        verify(guardianRepository, times(2)).findReadOnly(any(), eq(Guardian.class));
    }
}

