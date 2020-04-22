package pl.edu.pja.prz.mail.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

    @Mock
    private JavaMailSenderImpl javaMailSender;

    @Mock
    private JavaMailSenderFactory factory;

    @Mock
    private SpringTemplateEngine templateEngine;

    @InjectMocks
    private MailServiceImpl service;

    @Test
    public void Should_ReturnFalse_When_EmailOfRecipientIsEmpty() {
        //Given
        BaseMail baseMail = new BaseMail();

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_EmailOfRecipientIsNotCorrect() {
        //Given
        BaseMail baseMail = new BaseMail();
        baseMail.setTo("NOBODY LOL");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_SubjectIsEmpty() {
        //Given
        BaseMail baseMail = new BaseMail();
        baseMail.setTo("correct@email.com");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_ContentIsEmpty() {
        //Given
        BaseMail baseMail = new BaseMail();
        baseMail.setTo("correct@email.com");
        baseMail.setSubject("Hello there");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnFalse_When_EmailTemplateIsEmpty() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");
        baseMail.setEmailTemplate(null);

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertFalse(result);
    }

    @Test
    public void Should_ReturnTrue_When_BaseMailIsCorrect() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertTrue(result);
    }

    @Test
    public void Should_ReturnTrue_When_ContentIsEmptyButOtherVariablesAreSet() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                null);
        baseMail.addVariable("TEST_VAR", "TEST_VALUE");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertTrue(result);
    }

    @Test
    public void Should_SendEmail_When_BaseMailIsCorrect() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");

        //When
        when(factory.getSender()).thenReturn(javaMailSender);
        service.sendEmail(baseMail);

        //When
        verify(factory, only()).getSender();
    }

    @Test
    public void Should_SendEmail_When_BaseMailAndCredentialsAreCorrect() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");
        String email = "somemail@domain.com";
        String password = "pass";

        //When
        when(factory.getSender(anyString(), anyString())).thenReturn(javaMailSender);
        service.sendEmail(baseMail, email, password);

        //When
        verify(factory, only()).getSender(anyString(), anyString());
    }

    @Test
    public void ShouldNot_SendEmail_When_BaseMailIsCorrectAndCredentialsAreWrong() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");
        String email = "BAD_EMAIL";
        String password = "pass";

        //When
        service.sendEmail(baseMail, email, password);

        //When
        verify(factory, never()).getSender(anyString(), anyString());
    }

    @Test
    public void Should_CatchException_When_SendingEmail() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");
        String email = "correct_email@aa.eu";
        String password = "pass";

        //When
        when(factory.getSender(anyString(), anyString())).thenThrow(MailSendException.class);
        service.sendEmail(baseMail, email, password);

        //Then
        verify(factory, only()).getSender(anyString(), anyString());
    }

    @Test
    public void Should_CatchException_When_SendingEmail_Without_AdditionalEmailAndPassword() {
        //Given
        BaseMail baseMail = new BaseMail("correct@email.com", "Hello there",
                "Some text, which I want to show in email");

        //When
        when(factory.getSender()).thenThrow(MailSendException.class);
        service.sendEmail(baseMail);

        //Then
        verify(factory, only()).getSender();
    }


}
