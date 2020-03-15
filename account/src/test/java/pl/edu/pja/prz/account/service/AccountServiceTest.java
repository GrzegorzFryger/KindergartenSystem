package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Account;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    public void setUp() {
        accountService = new AccountService(accountRepository);
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


}
