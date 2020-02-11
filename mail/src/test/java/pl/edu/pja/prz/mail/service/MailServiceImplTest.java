package pl.edu.pja.prz.mail.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {

    @Mock
    private JavaMailSenderFactory factory;

    @InjectMocks
    private MailServiceImpl service;

    @BeforeEach
    public void setUp() {

    }

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
    public void Should_ReturnTrue_When_BaseMailIsCorrect() {
        //Given
        BaseMail baseMail = new BaseMail();
        baseMail.setTo("correct@email.com");
        baseMail.setSubject("Hello there");
        baseMail.setContent("Some text, which I want to show in email");

        //When
        boolean result = service.validateInput(baseMail);

        //Then
        assertTrue(result);
    }

}
