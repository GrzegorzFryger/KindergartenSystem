package pl.edu.pja.prz.core.controller.receivables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;
import pl.edu.pja.prz.receivables.model.CashPayment;

import java.util.List;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@RestController
@RequestMapping(API_RECEIVABLES)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class CashPaymentController {
    private final ReceivablesFacade receivablesFacade;

    @Autowired
    public CashPaymentController(ReceivablesFacade receivablesFacade) {
        this.receivablesFacade = receivablesFacade;
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
}
