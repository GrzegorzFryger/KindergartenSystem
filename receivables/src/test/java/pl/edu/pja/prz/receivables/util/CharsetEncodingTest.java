package pl.edu.pja.prz.receivables.util;

import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class CharsetEncodingTest {

    @Test
    public void Should_Return_Default_Charset_When_Input_Is_Incorrect() {
        //Given

        //When
        Charset charset = CharsetEncoding.charsetFromString("");

        //Then
        assertEquals(Charset.forName("Cp1250"), charset);
    }

    @Test
    public void Should_Return_Correct_Charset() {
        //Given

        //When
        Charset charset = CharsetEncoding.charsetFromString("utf8");
        Charset charset2 = CharsetEncoding.charsetFromString("UTF8");

        //Then
        assertEquals(StandardCharsets.UTF_8, charset);
        assertEquals(StandardCharsets.UTF_8, charset2);
    }
}