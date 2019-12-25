package pl.edu.pja.prz.receivables.service;

import pl.edu.pja.prz.receivables.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvParsingService {
    List<Transaction> getTransactionListFromCsv(File file) throws IOException;
    List<Transaction> getTransactionListFromCsv(File file, String charset) throws IOException;
}