package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.commons.util.DateUtils;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@RestController
@RequestMapping(API_RECEIVABLES)
public class CashPaymentController {
    private final ReceivablesFacade receivablesFacade;

    @Autowired
    public CashPaymentController(ReceivablesFacade receivablesFacade) {
        this.receivablesFacade = receivablesFacade;
    }

    @GetMapping("cash-payments")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<CashPayment> getAllCashPayments() {
        return receivablesFacade.getAllCashPayments();
    }

    @GetMapping("cash-payments/child/{childId}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<CashPayment> getAllCashPaymentsForChild(@PathVariable UUID childId) {
        return receivablesFacade.getAllCashPaymentsForChild(childId);
    }

    @GetMapping("cash-payments/past-month")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public List<CashPayment> getAllCashPaymentsForLastMonth() {
        LocalDate toDate = LocalDate.now();
        LocalDate fromDate = DateUtils.getDateOneMonthBehind(toDate);
        return receivablesFacade.getAllCashPaymentsForPastMonth(fromDate, toDate);
    }

    @GetMapping("cash-payments/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public CashPayment getCashPayment(@PathVariable Long id) {
        return receivablesFacade.getCashPayment(id);
    }

    @DeleteMapping("cash-payments/{id}")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void deleteCashPayment(@PathVariable Long id) {
        receivablesFacade.deleteCashPayment(id);
    }

    @PostMapping("cash-payments")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void createCashPayment(@RequestBody CashPayment cashPayment) {
        receivablesFacade.create(cashPayment);
    }

    @PutMapping("cash-payments")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public void updateCashPayment(@RequestBody CashPayment cashPayment) {
        receivablesFacade.update(cashPayment);
    }
}
