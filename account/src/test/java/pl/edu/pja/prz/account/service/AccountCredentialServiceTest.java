package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.repository.AccountRepository;
import pl.edu.pja.prz.account.utilites.PasswordManager;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountCredentialServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private ActivateTokenService activateTokenService;
    @Mock
    private PasswordManager passwordManager;
    @Mock
    private RoleService roleService;

    private AccountCredentialService accountCredentialService;

    @BeforeEach
    public void setUp() {
        accountCredentialService = new AccountCredentialService(accountRepository,
                passwordManager, roleService, activateTokenService);
    }

    @Test
    public void Should_FindByEmail() {
        //Given

        //When
        accountCredentialService.findByEmail("Some email");

        //Then
        verify(accountRepository, only()).findByEmail(anyString());
    }

    @Test
    public void Should_ThrowException_When_PasswordsForAccountActivationDoesNotMatch() {
        //Given
        CharSequence password = "password";
        CharSequence repeatedPassword = "oh no I'm different";

        //When
        Assertions.assertThrows(BusinessException.class, () -> {
            accountCredentialService.activateAccount("token", password, repeatedPassword);
        });

        //Then
    }

    @Test
    public void Should_ThrowException_When_AccountToUpdatePasswordDoesNotExists() {
        //Given
        CharSequence password = "password";
        CharSequence repeatedPassword = "password";

        //When
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            accountCredentialService.updatePassword(UUID.randomUUID(), password, repeatedPassword);
        });

        //Then
    }

    @Test
    public void Should_ThrowException_When_AccountToUpdateEmailDoesNotExists() {
        //Given

        //When
        when(accountRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            accountCredentialService.updateEmail(UUID.randomUUID(), "Some email");
        });

        //Then
    }

}
