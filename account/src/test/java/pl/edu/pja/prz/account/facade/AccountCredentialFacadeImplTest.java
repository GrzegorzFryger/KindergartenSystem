package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.mapper.AccountCredentialMapper;
import pl.edu.pja.prz.account.mapper.AccountCredentialMapperImpl;
import pl.edu.pja.prz.account.model.dto.AccountActivateDto;
import pl.edu.pja.prz.account.service.AccountCredentialService;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountCredentialFacadeImplTest {

    private AccountCredentialMapper accountCredentialMapper;

    @Mock
    private AccountCredentialService accountCredentialService;

    private AccountCredentialFacade accountCredentialFacade;

    @BeforeEach
    public void setUp() {
        accountCredentialMapper = new AccountCredentialMapperImpl();

        accountCredentialFacade = new AccountCredentialFacadeImpl(accountCredentialService, accountCredentialMapper);
    }

    @Test
    public void Should_FindByEmail() {
        //Given

        //When
        accountCredentialFacade.findByEmail("Some email");

        //Then
        verify(accountCredentialService, only()).findByEmail(anyString());
    }

    @Test
    public void Should_ActivateAccount() {
        //Given
        AccountActivateDto accountActivateDto = new AccountActivateDto();
        accountActivateDto.setRawPassword("Raw password");
        accountActivateDto.setRepeatRawPassword("Raw password");
        accountActivateDto.setToken("Token");

        //When
        accountCredentialFacade.activateAccount(accountActivateDto);

        //Then
        verify(accountCredentialService, only()).activateAccount(anyString(), anyString(), anyString());
    }
}
