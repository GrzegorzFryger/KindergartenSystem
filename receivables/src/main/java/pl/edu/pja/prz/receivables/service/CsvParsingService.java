package pl.edu.pja.prz.receivables.service;

import org.springframework.stereotype.Service;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public interface CsvParsingService {
    List<Transaction> getTransactionListFromCsv(File file) throws IOException;
}