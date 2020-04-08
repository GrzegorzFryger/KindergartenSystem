package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.account.model.enums.Gender;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PeselServiceImplTest {

    private PeselService peselService;

    @BeforeEach
    public void setUp() {
        peselService = new PeselServiceImpl();
    }

    @Test
    public void Should_ExtractFemaleGender() {
        //Given
        String[] femalePesels = {
                "02070803604",  //0
                "72072025522",  //2
                "03231075548",  //4
                "79021497665",  //6
                "77101378486"   //8
        };

        for (String pesel : femalePesels) {
            //When
            Gender gender = peselService.extractGender(pesel);

            //Then
            assertEquals(Gender.FEMALE, gender);
        }
    }

    @Test
    public void Should_ExtractMaleGender() {
        //Given
        String[] malePesels = {
                "79041184912",  //1
                "48120953237",  //3
                "61073047257",  //5
                "69051589276",  //7
                "90010754291"   //9
        };

        for (String pesel : malePesels) {
            //When
            Gender gender = peselService.extractGender(pesel);

            //Then
            assertEquals(Gender.MALE, gender);
        }
    }

    @Test
    public void Should_ExtractDateOfBirth() {
        //Given
        String pesel = "14210116376";   // Male - Born 01.01.2014
        String pesel2 = "97020163635";  // Female - Born 01.02.1997

        //When
        LocalDate result = peselService.extractDateOfBirth(pesel);
        LocalDate result2 = peselService.extractDateOfBirth(pesel2);

        //Then
        assertEquals(LocalDate.of(2014, 1, 1), result);
        assertEquals(LocalDate.of(1997, 2, 1), result2);
    }

    @Test
    public void Should_ThrowException_WhenDateInPeselIsIncorrect() {
        //Given
        String pesel = "97029963635";   // Male - Born 99.01.2014 (such date doesn't exists)

        //When
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            LocalDate result = peselService.extractDateOfBirth(pesel);
        });

        //Then
    }
}
