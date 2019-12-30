package pl.edu.pja.prz.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/receivables/")
public class ReceivablesController {
    private final ReceivablesFacade receivablesFacade;

    @Autowired
    public ReceivablesController(ReceivablesFacade receivablesFacade) {
        this.receivablesFacade = receivablesFacade;
    }

    @GetMapping("transactions")
    public List<Transaction> getAllTransactions() {
        return receivablesFacade.getAllUnassignedTransactions();
    }

    @GetMapping("transactions/{id}")
    public Transaction getAllTransactions(@PathVariable Long id) {
        return receivablesFacade.getTransaction(id);
    }

    @DeleteMapping("transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        receivablesFacade.delete(id);
    }

    @PostMapping("transactions")
    public void createTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.create(transaction);
    }

    @PutMapping("transactions")
    public void updateTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.update(transaction);
    }

    @PostMapping(value = "transactions/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Transaction> importTransactions(@RequestBody MultipartFile input,
                                                @RequestHeader(name = "Input-Encoding", required = false) String encoding)
            throws IOException {
        return receivablesFacade.getTransactionListFromCsv(input, encoding);
    }
}
