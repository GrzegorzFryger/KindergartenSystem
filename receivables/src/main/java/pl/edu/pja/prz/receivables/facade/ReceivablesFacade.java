package pl.edu.pja.prz.receivables.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.TransactionMapping;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.service.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ReceivablesFacade {
    private final CsvParsingService csvParsingService;
    private final TransactionService transactionService;
    private final TransactionMappingService transactionMappingService;
    private final IncomingPaymentsService incomingPaymentsService;
    private final CashPaymentService cashPaymentService;

    @Autowired
    public ReceivablesFacade(CsvParsingService csvParsingService, TransactionService transactionService,
                             TransactionMappingService transactionMappingService, IncomingPaymentsService incomingPaymentsService, CashPaymentService cashPaymentService) {
        this.csvParsingService = csvParsingService;
        this.transactionService = transactionService;
        this.transactionMappingService = transactionMappingService;
        this.incomingPaymentsService = incomingPaymentsService;
        this.cashPaymentService = cashPaymentService;
    }

    public Transaction getTransaction(Long id) {
        return transactionService.getTransaction(id);
    }

    public CashPayment getCashPayment(Long id) {
        return cashPaymentService.getCashPayment(id);
    }

    public List<Transaction> getAllUnassignedTransactions() {
        return transactionService.getAllUnassignedTransactions();
    }

    public List<Transaction> getAllTransactionsForChild(UUID childId) {
        return transactionService.getAllTransactionsByChildId(childId);
    }

    public List<Transaction> getAllTransactionsForPastMonth(LocalDate start, LocalDate end) {
        return transactionService.getAllTransactionsFromPastMonth(start, end);
    }

    public List<CashPayment> getAllCashPayments() {
        return cashPaymentService.getAllCashPayments();
    }

    public List<CashPayment> getAllCashPaymentsForChild(UUID childId) {
        return cashPaymentService.getAllCashPaymentsByChildId(childId);
    }

    public List<CashPayment> getAllCashPaymentsForPastMonth(LocalDate start, LocalDate end) {
        return cashPaymentService.getAllCashPaymentsFromPastMonth(start, end);
    }

    public void deleteTransaction(Long id) {
        transactionService.delete(id);
    }

    public void deleteCashPayment(Long id) {
        cashPaymentService.delete(id);
    }

    public void update(Transaction transaction) {
        transactionService.update(transaction);
    }

    public void update(CashPayment cashPayment) {
        cashPaymentService.update(cashPayment);
    }

    public void create(Transaction transaction) {
        transactionService.save(transaction);
    }

    public void create(CashPayment cashPayment) {
        cashPaymentService.save(cashPayment);
    }

    public List<Transaction> getTransactionListFromCsv(MultipartFile input, String charset) throws IOException {
        List<Transaction> transactions = csvParsingService.parseTransactionsFromCsvFile(input, charset);
        transactions.forEach(transactionMappingService::mapTransaction);
        transactions.forEach(transactionService::save);
        return transactions;
    }

    public List<Transaction> checkTransactionsReturnedInputFile(MultipartFile input, String charset) throws IOException {
        return csvParsingService.parseTransactionsFromCsvFile(input, charset);
    }

    public List<IncomingPaymentDto> getAllIncomingPaymentsByChildId(UUID childId) {
        return incomingPaymentsService.getAllPaymentsForChild(childId);
    }

    public List<IncomingPaymentDto> getAllIncomingPaymentsByGuardianId(UUID guardianId) {
        return incomingPaymentsService.getAllPaymentsForGuardian(guardianId);
    }

    public List<IncomingPaymentDto> getAllIncomingPaymentsByChildId(UUID childId, LocalDate from, LocalDate to) {
        return incomingPaymentsService.getAllPaymentsForChild(childId, from, to);
    }

    public List<IncomingPaymentDto> getAllIncomingPaymentsByGuardianId(UUID guardianId, LocalDate from, LocalDate to) {
        return incomingPaymentsService.getAllPaymentsForGuardian(guardianId, from, to);
    }

    public List<TransactionMapping> getAllMappingsForGuardian(UUID guardianId) {
        return transactionMappingService.getAllByGuardianId(guardianId);
    }

    public void addTransactionMapping(UUID guardianId, UUID childId) {
        transactionMappingService.create(guardianId, childId);
    }
}
