package pl.edu.pja.prz.receivables.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.service.CsvParsingService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CsvParsingServiceImplTest {
    private static final String CSV_RELATIVE_PATH = "/src/test/resources/test_transactions.csv";

    private File csv;
    private CsvParsingService service;

    @BeforeEach
    public void setUp() {
        service = new CsvParsingServiceImpl();

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
}