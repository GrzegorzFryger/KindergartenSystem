package pl.edu.pja.prz.receivables.service.impl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.builder.TransactionBuilder;
import pl.edu.pja.prz.receivables.service.CsvParsingService;
import pl.edu.pja.prz.receivables.service.FileService;
import pl.edu.pja.prz.receivables.util.CharsetEncoding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParsingServiceImpl implements CsvParsingService {
    private final FileService fileService;

    @Autowired
    public CsvParsingServiceImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public List<Transaction> parseTransactionsFromCsvFile(MultipartFile input, String charset) throws IOException {
        if (input == null) {
            throw new EmptyInputException("MultipartFile");
        }

        List<Transaction> transactions;
        File file = fileService.convertMultipartToFile(input);

        if (StringUtils.isNotEmpty(charset)) {
            transactions = getTransactionListFromCsv(file, charset);
        } else {
            transactions = getTransactionListFromCsv(file);
        }

        fileService.cleanUpFile(file);
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionListFromCsv(File file) throws IOException {
        return getTransactionListFromCsv(file, "UTF8");
    }

    @Override
    public List<Transaction> getTransactionListFromCsv(File file, String charset) throws IOException {
        List<Transaction> transactions = new ArrayList<>();

        CSVParser parser = CSVParser.parse(file, CharsetEncoding.charsetFromString(charset), getCSVFormat());
        for (CSVRecord csvRecord : parser) {
            if (isTransactionDateEmpty(csvRecord)) {
                break; //Stop iteration when empty record is found
            }
            transactions.add(mapTransactionFromCsvRecord(csvRecord));
        }
        parser.close();
        return transactions;
    }

    private CSVFormat getCSVFormat() {
        return CSVFormat.EXCEL
                .withDelimiter(';')
                .withQuote('\'')
                .withAllowDuplicateHeaderNames(true)
                .withFirstRecordAsHeader()
                .withTrim(true);
    }

    private boolean isTransactionDateEmpty(CSVRecord csvRecord) {
        return StringUtils.isEmpty(csvRecord.get(0));
    }

    private Transaction mapTransactionFromCsvRecord(CSVRecord csvRecord) {
        TransactionBuilder builder = new TransactionBuilder();
        return builder.fromCSVRecord(csvRecord).build();
    }
}
