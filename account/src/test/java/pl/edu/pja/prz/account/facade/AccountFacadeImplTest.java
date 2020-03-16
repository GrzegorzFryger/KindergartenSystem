package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.mapper.AccountMapper;
import pl.edu.pja.prz.account.mapper.AccountMapperImpl;
import pl.edu.pja.prz.account.service.AccountService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountFacadeImplTest {

    @Mock
    private AccountService accountService;
    private AccountMapper accountMapper;
    private AccountFacade accountFacade;

    @BeforeEach
    public void setUp() {
        accountMapper = new AccountMapperImpl();

        accountFacade = new AccountFacadeImpl(accountService, accountMapper);
    }

    @Test
    public void Should_FindAccountByEmail() {
        //Given

        //When
        accountFacade.findAccountByEmail("Some email");

        //Then
        verify(accountService, only()).findByEmail(anyString());
    }
}
