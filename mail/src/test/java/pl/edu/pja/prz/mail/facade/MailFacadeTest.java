package pl.edu.pja.prz.mail.facade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.service.MailService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MailFacadeTest {

    @Mock
    private MailService mailService;

    @InjectMocks
    private MailFacade facade;

    @Test
    public void Should_DelegateSendEmailMethodProperly() {
        //Given
        BaseMail baseMail = new BaseMail();

        //When
        facade.sendEmail(baseMail);

        //Then
        verify(mailService, only()).sendEmail(any(BaseMail.class));
    }

    @Test
    public void Should_DelegateSendEmailMethodProperly_2() {
        //Given
        BaseMail baseMail = new BaseMail();

        //When
        facade.sendEmail(baseMail, "USER", "PASS");

        //Then
        verify(mailService, only()).sendEmail(any(BaseMail.class), anyString(), anyString());
    }
}
