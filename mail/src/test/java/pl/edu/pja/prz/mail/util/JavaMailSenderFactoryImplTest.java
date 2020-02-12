package pl.edu.pja.prz.mail.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import pl.edu.pja.prz.mail.model.MailProperties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class JavaMailSenderFactoryImplTest {


    private MailProperties mailProperties;
    private JavaMailSenderFactoryImpl javaMailSenderFactory;

    @BeforeEach
    public void setUp() {
        mailProperties = new MailProperties();
        mailProperties.setHost("HOST");
        mailProperties.setPort("10000");
        mailProperties.setUsername("USER");
        mailProperties.setPassword("PASS");
        mailProperties.setProtocol("SMTP");
        mailProperties.setSmtpAuth(true);
        mailProperties.setSmtpStartTls(true);
        mailProperties.setDebug(true);
        mailProperties.setSmtpConnectionTimeout("5000");
        mailProperties.setSmtpTimeout("5000");
        mailProperties.setSmtpwWitetimeout("5000");


        javaMailSenderFactory = new JavaMailSenderFactoryImpl(mailProperties);
    }

    @Test
    public void Should_GetProperSender() {
        //Given

        //When
        JavaMailSenderImpl result = (JavaMailSenderImpl) javaMailSenderFactory.getSender();

        //Then
        assertNotNull(result);
        assertEquals("USER", result.getUsername());
        assertEquals("PASS", result.getPassword());
    }

    @Test
    public void Should_GetProperSenderWithUserAndPasswordProvided() {
        //Given

        //When
        JavaMailSenderImpl result = (JavaMailSenderImpl) javaMailSenderFactory.getSender("USER_1", "PASS_1");

        //Then
        assertNotNull(result);
        assertEquals("USER_1", result.getUsername());
        assertEquals("PASS_1", result.getPassword());
    }

}
