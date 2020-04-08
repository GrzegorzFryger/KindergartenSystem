package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.receivables.service.FileService;

class FileServiceImplTest {
    private FileService fileService;

    @BeforeEach
    public void setUp() {
        fileService = new FileServiceImpl();
    }

    @Test
    public void Should_ThrowException_When_FileToConvertIsNull() {
        //Given

        //When
        Assertions.assertThrows(NullPointerException.class, () -> {
            fileService.convertMultipartToFile(null);
        });

        //Then
    }

    @Test
    public void Should_ThrowException_When_FileToCleanUpIsNull() {
        //Given

        //When
        Assertions.assertThrows(NullPointerException.class, () -> {
            fileService.cleanUpFile(null);
        });

        //Then
    }
}
