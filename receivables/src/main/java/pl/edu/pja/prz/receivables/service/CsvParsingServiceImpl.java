package pl.edu.pja.prz.receivables.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.builder.TransactionBuilder;
import pl.edu.pja.prz.receivables.util.CharsetEncoding;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParsingServiceImpl implements CsvParsingService {

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
        }
        parser.close();
        return transactions;
    }

    @Override
    public File convertMultipartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
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