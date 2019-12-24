package pl.edu.pja.prz.receivables.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.service.CsvParsingService;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ReceivablesFacade {
    private final CsvParsingService csvParsingService;
    private final TransactionService transactionService;

    @Autowired
    public ReceivablesFacade(CsvParsingService csvParsingService, TransactionService transactionService) {
        this.csvParsingService = csvParsingService;
        this.transactionService = transactionService;
    }

    public Transaction getTransaction(Long id) {
        return transactionService.getTransaction(id);
    }

    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    public void delete(Long id) {
        transactionService.delete(id);
    }

    public void update(Transaction transaction) {
        transactionService.update(transaction);
    }

    public void create(Transaction transaction) {
        transactionService.create(transaction);
    }

    public List<Transaction> getTransactionListFromCsv(File file) throws IOException {
        return csvParsingService.getTransactionListFromCsv(file);
    }

    public List<Transaction> getTransactionListFromCsv(File file, String charset) throws IOException {
        return csvParsingService.getTransactionListFromCsv(file, charset);
    }
}
