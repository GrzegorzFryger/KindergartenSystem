package pl.edu.pja.prz.mail.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.spring5.SpringTemplateEngine;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

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

}
