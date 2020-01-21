package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;
import pl.edu.pja.prz.receivables.model.Transaction;
import pl.edu.pja.prz.receivables.model.dto.IncomingPaymentDto;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public Transaction getTransaction(@PathVariable Long id) {
        return receivablesFacade.getTransaction(id);
    }

    @DeleteMapping("transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        receivablesFacade.deleteTransaction(id);
    }

    @PostMapping("transactions")
    public void createTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.create(transaction);
    }

    @PutMapping("transactions")
    public void updateTransaction(@RequestBody Transaction transaction) {
        receivablesFacade.update(transaction);
    }

    @GetMapping("cash-payments")
    public List<CashPayment> getAllCashPayments() {
        return receivablesFacade.getAllCashPayments();
    }

    @GetMapping("cash-payments/{id}")
    public CashPayment getCashPayment(@PathVariable Long id) {
        return receivablesFacade.getCashPayment(id);
    }

    @DeleteMapping("cash-payments/{id}")
    public void deleteCashPayment(@PathVariable Long id) {
        receivablesFacade.deleteCashPayment(id);
    }

    @PostMapping("cash-payments")
    public void createCashPayment(@RequestBody CashPayment cashPayment) {
        receivablesFacade.create(cashPayment);
    }

    @PutMapping("cash-payments")
    public void updateCashPayment(@RequestBody CashPayment cashPayment) {
        receivablesFacade.update(cashPayment);
    }

    @GetMapping("payments/child/{childId}")
    public List<IncomingPaymentDto> getAllIncomingPaymentsForChild(@PathVariable UUID childId) {
        return receivablesFacade.getAllIncomingPaymentsByChildId(childId);
    }

    @GetMapping("payments/child/{childId}/{from}/{to}")
    public List<IncomingPaymentDto> getAllIncomingPaymentsForChild(@PathVariable UUID childId,
                                                                   @PathVariable String from,
                                                                   @PathVariable String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return receivablesFacade.getAllIncomingPaymentsByChildId(childId, fromDate, toDate);
    }

    @GetMapping("payments/guardian/{guardianId}")
    public List<IncomingPaymentDto> getAllIncomingPaymentsForGuardian(@PathVariable UUID guardianId) {
        return receivablesFacade.getAllIncomingPaymentsByGuardianId(guardianId);
    }

    @GetMapping("payments/guardian/{guardianId}/{from}/{to}")
    public List<IncomingPaymentDto> getAllIncomingPaymentsForGuardian(@PathVariable UUID guardianId,
                                                                      @PathVariable String from,
                                                                      @PathVariable String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        return receivablesFacade.getAllIncomingPaymentsByGuardianId(guardianId, fromDate, toDate);
    }

    @PostMapping(value = "transactions/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<Transaction> importTransactions(@RequestBody MultipartFile input,
                                                @RequestHeader(name = "Input-Encoding", required = false) String encoding)
            throws IOException {
        return receivablesFacade.getTransactionListFromCsv(input, encoding);
    }
}
