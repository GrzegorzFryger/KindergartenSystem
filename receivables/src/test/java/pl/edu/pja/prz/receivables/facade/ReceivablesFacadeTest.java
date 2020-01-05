package pl.edu.pja.prz.receivables.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.service.CsvParsingService;
import pl.edu.pja.prz.receivables.service.IncomingPaymentsService;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReceivablesFacadeTest {
    private final Long TEST_ID = 1L;

    @Mock
    private CsvParsingService csvParsingService;
    @Mock
    private TransactionService transactionService;
    @Mock
    private TransactionMappingService transactionMappingService;
    @Mock
    private IncomingPaymentsService incomingPaymentsService;

    private Transaction transaction;

    private ReceivablesFacade facade;

    @BeforeEach
    public void setUp() {
        facade = new ReceivablesFacade(csvParsingService, transactionService,
                transactionMappingService, incomingPaymentsService);

        transaction = new Transaction();
    }

    @Test
    public void Should_GetTransaction() {
        //Given

        //When
        when(transactionService.getTransaction(anyLong())).thenReturn(transaction);
        Transaction result = facade.getTransaction(TEST_ID);

        //Then
        verify(transactionService, times(1)).getTransaction(TEST_ID);
        assertNotNull(result);
    }

    @Test
    public void Should_GetAllUnassignedTransactions() {
        //Given
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(transactionService.getAllUnassignedTransactions()).thenReturn(transactionList);
        List<Transaction> result = facade.getAllUnassignedTransactions();

        //Then
        verify(transactionService, times(1)).getAllUnassignedTransactions();
        assertEquals(1, result.size());
    }

    @Test
    public void Should_DeleteTransaction() {
        //Given

        //When
        facade.delete(TEST_ID);

        //Then
        verify(transactionService, times(1)).delete(TEST_ID);
    }

    @Test
    public void Should_UpdateTransaction() {
        //Given

        //When
        facade.update(transaction);

        //Then
        verify(transactionService, times(1)).update(any(Transaction.class));
    }

    @Test
    public void Should_CreateTransaction() {
        //Given

        //When
        facade.create(transaction);

        //Then
        verify(transactionService, times(1)).save(any(Transaction.class));
    }

    @Test
    public void Should_GetAllTransactionsFromCsv_When_EncodingHeaderIsProvided() throws IOException {
        //Given
        MultipartFile input = new MockMultipartFile("input.csv", new byte[10]);
        File file = new File("SOME PATH");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(csvParsingService.convertMultipartToFile(any(MultipartFile.class))).thenReturn(file);
        when(csvParsingService.getTransactionListFromCsv(any(File.class), anyString())).thenReturn(transactionList);
        List<Transaction> result = facade.getTransactionListFromCsv(input, "UTF8");

        //Then
        verify(csvParsingService, times(1)).getTransactionListFromCsv(any(File.class), anyString());
        verify(csvParsingService, never()).getTransactionListFromCsv(any(File.class));
        verify(csvParsingService, times(1)).cleanUpFile(any(File.class));
        assertEquals(1, result.size());
    }

    @Test
    public void Should_GetAllTransactionsFromCsv_When_EncodingHeaderIsNotProvided() throws IOException {
        //Given
        MultipartFile input = new MockMultipartFile("input.csv", new byte[10]);
        File file = new File("SOME PATH");
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(csvParsingService.convertMultipartToFile(any(MultipartFile.class))).thenReturn(file);
        when(csvParsingService.getTransactionListFromCsv(any(File.class))).thenReturn(transactionList);
        List<Transaction> result = facade.getTransactionListFromCsv(input, "");

        //Then
        verify(csvParsingService, times(1)).getTransactionListFromCsv(any(File.class));
        verify(csvParsingService, never()).getTransactionListFromCsv(any(File.class), anyString());
        verify(csvParsingService, times(1)).cleanUpFile(any(File.class));
        assertEquals(1, result.size());
    }
}