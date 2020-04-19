package pl.edu.pja.prz.finances.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.finances.model.AccountNumber;
import pl.edu.pja.prz.finances.model.dto.AccountNumberDto;
import pl.edu.pja.prz.finances.repository.AccountNumberRepository;
import pl.edu.pja.prz.finances.service.AccountNumberService;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountNumberServiceImplTest {

    @Mock
    private AccountNumberRepository accountRepository;

    private AccountNumberService service;

    @BeforeEach
    public void setUp() {
        service = new AccountNumberServiceImpl(accountRepository);
    }

    @Test
    public void Should_ReturnAccountNumber() {
        //Given
        AccountNumber accountNumber = new AccountNumber();
        accountNumber.setAccountNumber("1234");
        Long id = 1L;

        //When
        when(accountRepository.findById(anyLong())).thenReturn(Optional.of(accountNumber));
        AccountNumberDto result = service.getAccountNumber(UUID.randomUUID());

        //Then
        verify(accountRepository, only()).findById(id);
        assertNotNull(result);
        assertEquals("1234", result.getAccountNumber());
    }
}
