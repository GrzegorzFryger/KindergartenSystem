package pl.edu.pja.prz.mail.model.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseMailTestDtoTest {

    @Test
    public void SimpleGetterAndSetterTest() {
        //Given
        BaseMailTestDto baseMailTestDto = new BaseMailTestDto();
        baseMailTestDto.setContent("Content");
        baseMailTestDto.setSubject("Subject");
        baseMailTestDto.setTo("To");

        //When

        //Then
        assertEquals("Content", baseMailTestDto.getContent());
        assertEquals("Subject", baseMailTestDto.getSubject());
        assertEquals("To", baseMailTestDto.getTo());
    }
}
