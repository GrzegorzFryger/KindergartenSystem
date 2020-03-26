package pl.edu.pja.prz.mail.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmailTemplateTest {

    @Test
    public void Should_ReturnProperString() {
        //Given
        EmailTemplate template = EmailTemplate.BASE_TEMPLATE;

        //When
        String result = template.toString();

        //Then
        assertEquals("base-email", result);
    }
}
