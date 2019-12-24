package pl.edu.pja.prz.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("api/receivables/")
public class ReceivablesController {
    private final TransactionService transactionService;

    @Autowired
    public ReceivablesController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("transactions")
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("transactions/{id}")
    public Transaction getAllTransactions(@PathVariable Long id) {
        return transactionService.getTransaction(id);
    }

    @DeleteMapping("transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.delete(id);
    }

    @PostMapping("transactions")
    public void createTransaction(@RequestBody Transaction transaction) {
        transactionService.create(transaction);
    }

    @PutMapping("transactions")
    public void updateTransaction(@RequestBody Transaction transaction) {
        transactionService.update(transaction);
    }
}
