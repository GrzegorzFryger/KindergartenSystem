package pl.edu.pja.prz.receivables.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.service.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    @Mock
    private CashPaymentService cashPaymentService;

    private Transaction transaction;
    private CashPayment cashPayment;
    private IncomingPaymentDto dto;

    private ReceivablesFacade facade;

    @BeforeEach
    public void setUp() {
        facade = new ReceivablesFacade(csvParsingService, transactionService,
                transactionMappingService, incomingPaymentsService, cashPaymentService);

        transaction = new Transaction();
        cashPayment = new CashPayment();
        dto = new IncomingPaymentDto();
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
    public void Should_GetCashPayment() {
        //Given

        //When
        when(cashPaymentService.getCashPayment(anyLong())).thenReturn(cashPayment);
        CashPayment result = facade.getCashPayment(TEST_ID);

        //Then
        verify(cashPaymentService, times(1)).getCashPayment(TEST_ID);
        assertNotNull(result);
    }

    @Test
    public void Should_GetAllCashPayments() {
        //Given
        List<CashPayment> cashPaymentList = new ArrayList<>();
        cashPaymentList.add(cashPayment);

        //When
        when(cashPaymentService.getAllCashPayments()).thenReturn(cashPaymentList);
        List<CashPayment> result = facade.getAllCashPayments();

        //Then
        assertEquals(1, result.size());
        verify(cashPaymentService, only()).getAllCashPayments();
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
        facade.deleteTransaction(TEST_ID);

        //Then
        verify(transactionService, times(1)).delete(TEST_ID);
    }

    @Test
    public void Should_DeleteCashPayment() {
        //Given

        //When
        facade.deleteCashPayment(TEST_ID);

        //Then
        verify(cashPaymentService, times(1)).delete(TEST_ID);
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
    public void Should_UpdateCashPayment() {
        //Given

        //When
        facade.update(cashPayment);

        //Then
        verify(cashPaymentService, times(1)).update(any(CashPayment.class));
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
    public void Should_CreateCashPayment() {
        //Given

        //When
        facade.create(cashPayment);

        //Then
        verify(cashPaymentService, times(1)).save(any(CashPayment.class));
    }

    @Test
    public void Should_GetAllTransactionsFromCsv_When_EncodingHeaderIsProvided() throws IOException {
        //Given
        MultipartFile input = new MockMultipartFile("input.csv", new byte[10]);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(csvParsingService.parseTransactionsFromCsvFile(any(MultipartFile.class), anyString())).thenReturn(transactionList);
        List<Transaction> result = facade.getTransactionListFromCsv(input, "UTF8");

        //Then
        assertNotNull(result);
        verify(csvParsingService, times(1)).parseTransactionsFromCsvFile(any(MultipartFile.class), anyString());
        verify(transactionService, times(1)).save(any(Transaction.class));
        verify(transactionMappingService, times(1)).mapTransaction(any(Transaction.class));
        assertEquals(1, result.size());
    }

    @Test
    public void Should_GetAllTransactionsFromCsv_When_EncodingHeaderIsNotProvided() throws IOException {
        //Given
        MultipartFile input = new MockMultipartFile("input.csv", new byte[10]);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(csvParsingService.parseTransactionsFromCsvFile(any(MultipartFile.class), anyString())).thenReturn(transactionList);
        List<Transaction> result = facade.getTransactionListFromCsv(input, "");

        //Then
        assertNotNull(result);
        verify(csvParsingService, times(1)).parseTransactionsFromCsvFile(any(MultipartFile.class), anyString());
        verify(transactionService, times(1)).save(any(Transaction.class));
        verify(transactionMappingService, times(1)).mapTransaction(any(Transaction.class));
        assertEquals(1, result.size());
    }

    @Test
    public void Should_checkTransactionsReturnedInputFile() throws IOException {
        //Given
        MultipartFile input = new MockMultipartFile("input.csv", new byte[10]);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);

        //When
        when(csvParsingService.parseTransactionsFromCsvFile(any(MultipartFile.class), anyString())).thenReturn(transactionList);
        List<Transaction> result = facade.checkTransactionsReturnedInputFile(input, "");

        //Then
        assertNotNull(result);
        verify(csvParsingService, times(1)).parseTransactionsFromCsvFile(any(MultipartFile.class), anyString());
        verify(transactionService, never()).save(any(Transaction.class));
        verify(transactionMappingService, never()).mapTransaction(any(Transaction.class));
        assertEquals(1, result.size());
    }

    @Test
    public void Should_GetAllIncomingPaymentsByChildId() {
        //Given
        List<IncomingPaymentDto> dtos = new ArrayList<>();
        dtos.add(dto);

        //When
        when(incomingPaymentsService.getAllPaymentsForChild(any(UUID.class))).thenReturn(dtos);
        List<IncomingPaymentDto> result = facade.getAllIncomingPaymentsByChildId(UUID.randomUUID());

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incomingPaymentsService, times(1)).getAllPaymentsForChild(any(UUID.class));
    }

    @Test
    public void Should_GetAllIncomingPaymentsByGuardianId() {
        //Given
        List<IncomingPaymentDto> dtos = new ArrayList<>();
        dtos.add(dto);

        //When
        when(incomingPaymentsService.getAllPaymentsForGuardian(any(UUID.class))).thenReturn(dtos);
        List<IncomingPaymentDto> result = facade.getAllIncomingPaymentsByGuardianId(UUID.randomUUID());

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incomingPaymentsService, times(1)).getAllPaymentsForGuardian(any(UUID.class));
    }

    @Test
    public void Should_GetAllIncomingPaymentsByChildIdBetweenDates() {
        //Given
        List<IncomingPaymentDto> dtos = new ArrayList<>();
        dtos.add(dto);
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        //When
        when(incomingPaymentsService
                .getAllPaymentsForChild(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(dtos);
        List<IncomingPaymentDto> result = facade.getAllIncomingPaymentsByChildId(UUID.randomUUID(), start, end);


        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incomingPaymentsService, times(1))
                .getAllPaymentsForChild(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_GetAllIncomingPaymentsByGuardianIdBetweenDates() {
        //Given
        List<IncomingPaymentDto> dtos = new ArrayList<>();
        dtos.add(dto);
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();

        //When
        when(incomingPaymentsService
                .getAllPaymentsForGuardian(any(UUID.class), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(dtos);
        List<IncomingPaymentDto> result = facade.getAllIncomingPaymentsByGuardianId(UUID.randomUUID(), start, end);

        //Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(incomingPaymentsService, times(1))
                .getAllPaymentsForGuardian(any(UUID.class), any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    public void Should_GetAllMappingsForGuardian() {
        //Given
        List<TransactionMapping> transactionMappings = new ArrayList<>();
        transactionMappings.add(new TransactionMapping());
        transactionMappings.add(new TransactionMapping());

        //When
        when(transactionMappingService.getAllByGuardianId(any(UUID.class))).thenReturn(transactionMappings);
        List<TransactionMapping> result = facade.getAllMappingsForGuardian(UUID.randomUUID());


        //Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(transactionMappingService, only()).getAllByGuardianId(any(UUID.class));
    }

    @Test
    public void Should_AddTransactionMapping() {
        //Given
        UUID childId = UUID.randomUUID();
        UUID guardianId = UUID.randomUUID();

        //When
        facade.addTransactionMapping(guardianId, childId);

        //Then
        verify(transactionMappingService, only()).create(any(UUID.class), any(UUID.class));
    }
}
