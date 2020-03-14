package pl.edu.pja.prz.receivables.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.service.CsvParsingService;
import pl.edu.pja.prz.receivables.service.FileService;

@ExtendWith(MockitoExtension.class)
class CsvParsingServiceImplTest {
    private static final String CSV_RELATIVE_PATH = "/src/test/resources/test_transactions.csv";

    @Mock
    private FileService fileService;

    private File csv;
    private CsvParsingService service;

    @BeforeEach
    public void setUp() {
        service = new CsvParsingServiceImpl(fileService);

        String userDirectory = System.getProperty("user.dir");
        csv = new File(userDirectory + CSV_RELATIVE_PATH);
    }

    @Test
    public void Should_Read_All_Transactions_From_CSV_File() throws IOException {
        //Given

        //When
        List<Transaction> transactions = service.getTransactionListFromCsv(csv);

        //Then
        assertNotNull(transactions);
        assertEquals(5, transactions.size());

        assertNotNull(transactions.get(0).getTransactionDate());
        assertNotNull(transactions.get(0).getTransactionAmount());
        assertNotNull(transactions.get(0).getTitle());
        assertNotNull(transactions.get(0).getTransactionCurrency());
    }

    @Test
    public void Should_ThrowException_When_InputIsEmpty() throws IOException {
        //Given

        //When
        Assertions.assertThrows(EmptyInputException.class, () -> {
            List<Transaction> result = service.parseTransactionsFromCsvFile(null, "CP1250");
        });

        //Then
    }

    @Test
    public void Should_parseTransactionsFromCsvFile_WhenNoCharsetWasProvided() throws IOException {
        //Given
        MultipartFile input = mock(MultipartFile.class);

        //When
        when(fileService.convertMultipartToFile(any(MultipartFile.class))).thenReturn(csv);
        List<Transaction> result = service.parseTransactionsFromCsvFile(input, null);

        //Then
        assertNotNull(result);
        assertEquals(5, result.size());

        assertNotNull(result.get(0).getTransactionDate());
        assertNotNull(result.get(0).getTransactionAmount());
        assertNotNull(result.get(0).getTitle());
        assertNotNull(result.get(0).getTransactionCurrency());
    }

    @Test
    public void Should_parseTransactionsFromCsvFile_WhenCharsetWasProvided() throws IOException {
        //Given
        MultipartFile input = mock(MultipartFile.class);

        //When
        when(fileService.convertMultipartToFile(any(MultipartFile.class))).thenReturn(csv);
        List<Transaction> result = service.parseTransactionsFromCsvFile(input, "UTF-8");

        //Then
        assertNotNull(result);
        assertEquals(5, result.size());

        assertNotNull(result.get(0).getTransactionDate());
        assertNotNull(result.get(0).getTransactionAmount());
        assertNotNull(result.get(0).getTitle());
        assertNotNull(result.get(0).getTransactionCurrency());
    }
}
