package pl.edu.pja.prz.receivables.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.util.CharsetEncoding;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvParsingServiceImpl implements CsvParsingService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public List<Transaction> getTransactionListFromCsv(File file) throws IOException {
        return getTransactionListFromCsv(file, "Cp1250");
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
            System.out.println(csvRecord);
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
        Transaction transaction = new Transaction();
        transaction.setTransactionDate(parseDate(csvRecord.get(0)));
        transaction.setBookingDate(parseDate(csvRecord.get(1)));
        transaction.setContractorDetails(csvRecord.get(2));
        transaction.setTitle(csvRecord.get(3));
        transaction.setAccountNumber(csvRecord.get(4));
        transaction.setBankName(csvRecord.get(5));
        transaction.setDetails(csvRecord.get(6));
        if (StringUtils.isNotEmpty(csvRecord.get(8))) {
            transaction.setTransactionAmount(new BigDecimal(csvRecord.get(8).replace(',', '.')));
        }
        transaction.setTransactionCurrency(csvRecord.get(9));
        return transaction;
    }

    private LocalDate parseDate(String date) {
        if (StringUtils.isEmpty(date)) {
            return null;
        } else {
            return LocalDate.parse(date, formatter);
        }
    }
}