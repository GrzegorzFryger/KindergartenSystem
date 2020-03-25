package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.account.model.value.Password;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    private Account account;
    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(accountRepository);
        account = new Account();

        account.setAddress( new Address("70-700", "City", "Street 256") );
        account.setFullName( new FullName("TestName", "TestSurname"));
        account.setPhoneNumber( new Phone("123132123") );
        account.setPassword(new Password("newPassword"));
        account.setAccountStatus( AccountStatus.ACTIVE);
        account.setId( UUID.randomUUID() );
        account.setEmail("test@test.com");
    }

    @Test
    public void Should_FindById() {
        //Given

        Optional<Account> accountOptional = Optional.of(new Account());

        //When
        when(accountRepository.findById(any(UUID.class))).thenReturn(accountOptional);
        accountService.findById(UUID.randomUUID());

        //Then
        verify(accountRepository, only()).findById(any(UUID.class));
    }

    @Test
    public void Should_ThrowException_When_RepositoryCouldNotFindAccountById() {
        //Given

        //When
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            accountService.findById(UUID.randomUUID());
        });

        //Then
    }

    @Test
    public void Should_FindByEmail() {
        //Given
        Optional<Account> accountOptional = Optional.of(new Account());

        //When
        when(accountRepository.findByEmail(anyString())).thenReturn(accountOptional);
        accountService.findByEmail("Some email");

        //Then
        verify(accountRepository, only()).findByEmail(anyString());
    }

    @Test
    public void Should_ThrowException_When_RepositoryCouldNotFindAccountByEmail() {
        //Given

        //When
        when(accountRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            accountService.findByEmail("Some email");
        });

        //Then
    }

    @Test
    public void Should_ThrowException_When_AccountForUpdatingPersonalDataDoesNotExists() {
        //Given
        Person person = new Person();
        person.setId(UUID.randomUUID());

        //When
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            accountService.updatePersonalData(person);
        });

        //Then
    }

    @Test
    void Should_UpdatePersonalData() {
        //given
        ArgumentCaptor<Account> argument = ArgumentCaptor.forClass(Account.class);
        var updatedAccount = new Person();

        updatedAccount.setAddress( new Address("80-80", "CityUpdated", "StreetUpdated") );
        updatedAccount.setFullName( new FullName("TestNameUpdated", "TestSurnameUpdated"));
        updatedAccount.setPhoneNumber( new Phone("723456478") );
        updatedAccount.setId( account.getId() );

        //when
        when(accountRepository.findById(account.getId())).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());
        accountService.updatePersonalData(updatedAccount);

        //then
        verify(accountRepository,times(1)).findById(account.getId());
        verify(accountRepository,times(1)).save(any());

        verify(accountRepository).save(argument.capture());
        assertEquals(updatedAccount.getAddress(),argument.getValue().getAddress());
        assertEquals(updatedAccount.getFullName(),argument.getValue().getFullName());
        assertEquals(updatedAccount.getAddress(),argument.getValue().getAddress());
        assertEquals(updatedAccount.getPhoneNumber(),argument.getValue().getPhoneNumber());

        assertEquals(account.getEmail(),argument.getValue().getEmail());
        assertEquals(account.getPassword(),argument.getValue().getPassword());
    }

    @Test
    void Should_UpdateNotPersonalData() {
        //given
        Person person = account;
        var personUpdated = new Person();

        personUpdated.setAddress( new Address("80-80", "CityUpdated", "StreetUpdated") );
        personUpdated.setFullName( new FullName("TestNameUpdated", "TestSurnameUpdated"));
        personUpdated.setPhoneNumber( new Phone("723456478") );
        personUpdated.setId( account.getId() );

        this.accountService.updateNotEmptyPersonField(person, personUpdated);

        assertEquals(personUpdated, person);
    }

    @Test
    void Should_Not_UpdateNotPersonalData() {
        //given
        Person person = account;
        var personUpdated = new Person();

        personUpdated.setAddress( null );
        personUpdated.setFullName( null);
        personUpdated.setPhoneNumber( null );
        personUpdated.setId( account.getId() );

        this.accountService.updateNotEmptyPersonField(person, personUpdated);

        assertEquals(account, person);
    }
}
