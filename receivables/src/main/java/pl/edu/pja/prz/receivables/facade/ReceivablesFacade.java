package pl.edu.pja.prz.receivables.facade;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;
import pl.edu.pja.prz.receivables.service.CsvParsingService;
import pl.edu.pja.prz.receivables.service.IncomingPaymentsService;
import pl.edu.pja.prz.receivables.service.TransactionMappingService;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.io.File;
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

    @Autowired
    public ReceivablesFacade(CsvParsingService csvParsingService, TransactionService transactionService,
                             TransactionMappingService transactionMappingService, IncomingPaymentsService incomingPaymentsService) {
        this.csvParsingService = csvParsingService;
        this.transactionService = transactionService;
        this.transactionMappingService = transactionMappingService;
        this.incomingPaymentsService = incomingPaymentsService;
    }

    public Transaction getTransaction(Long id) {
        return transactionService.getTransaction(id);
    }

    public List<Transaction> getAllUnassignedTransactions() {
        return transactionService.getAllUnassignedTransactions();
    }

    public void delete(Long id) {
        transactionService.delete(id);
    }

    public void update(Transaction transaction) {
        transactionService.update(transaction);
    }

    public void create(Transaction transaction) {
        transactionService.save(transaction);
    }

    public List<Transaction> getTransactionListFromCsv(MultipartFile input, String charset) throws IOException {
        List<Transaction> transactions;
        File file = csvParsingService.convertMultipartToFile(input);

        if (StringUtils.isNotEmpty(charset)) {
            transactions = csvParsingService.getTransactionListFromCsv(file, charset);
        } else {
            transactions = csvParsingService.getTransactionListFromCsv(file);
        }

        csvParsingService.cleanUpFile(file);

        transactions.forEach(transactionMappingService::mapTransaction);
        transactions.forEach(transactionService::save);

        return transactions;
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
}
