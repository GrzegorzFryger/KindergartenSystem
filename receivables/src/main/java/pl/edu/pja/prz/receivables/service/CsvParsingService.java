package pl.edu.pja.prz.receivables.service;

import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvParsingService {
    List<Transaction> getTransactionListFromCsv(File file) throws IOException;
    List<Transaction> getTransactionListFromCsv(File file, String charset) throws IOException;
    File convertMultipartToFile(MultipartFile file) throws IOException;
    void cleanUpFile(File file) throws IOException;
}