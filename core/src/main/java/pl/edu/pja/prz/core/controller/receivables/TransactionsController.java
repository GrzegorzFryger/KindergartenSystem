package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@RestController
@RequestMapping(API_RECEIVABLES)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class TransactionsController {
    private final ReceivablesFacade receivablesFacade;

    @Autowired
    public TransactionsController(ReceivablesFacade receivablesFacade) {
        this.receivablesFacade = receivablesFacade;
    }

    @GetMapping("transactions")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<Transaction> getAllTransactions() {
        return receivablesFacade.getAllUnassignedTransactions();
    }

    @GetMapping("transactions/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public Transaction getTransaction(@PathVariable Long id) {
        return receivablesFacade.getTransaction(id);
    }

    @DeleteMapping("transactions/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void deleteTransaction(@PathVariable Long id) {
        receivablesFacade.deleteTransaction(id);
    }

    @PostMapping("transactions")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void createTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.create(transaction);
    }

    @PutMapping("transactions")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void updateTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.update(transaction);
    }
}