package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.commons.util.DateUtils;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@RestController
@RequestMapping(API_RECEIVABLES)
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

    @GetMapping("transactions/child/{childId}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<Transaction> getAllTransactionsForChild(@PathVariable UUID childId) {
        return receivablesFacade.getAllTransactionsForChild(childId);
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

    //TODO: Change to PUT after fixing CORS issues
    @PostMapping("transactions/assign/{childId}/{guardianId}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void assignTransactionToChild(@RequestBody Transaction transaction,
                                         @PathVariable UUID childId,
                                         @PathVariable UUID guardianId) {
        transaction.setChildId(childId);
        transaction.setGuardianId(guardianId);
        receivablesFacade.update(transaction);
    }

    @PutMapping("transactions")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void updateTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.update(transaction);
    }

    @GetMapping("transactions/past-month")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<Transaction> getAllIncomingPaymentsForLastMonth() {
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = DateUtils.getDateOneMonthBehind(toDate);
        return receivablesFacade.getAllTransactionsForPastMonth(fromDate, toDate);
    }
}
