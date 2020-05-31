package pl.edu.pja.prz.core.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.payments.facade.PaymentHistoryFacade;
import pl.edu.pja.prz.payments.model.dto.PaymentHistoryDto;

import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;

@RestController
@RequestMapping(API_PAYMENTS)
public class PaymentHistoryController {
    private final PaymentHistoryFacade paymentHistoryFacade;

    public PaymentHistoryController(PaymentHistoryFacade paymentHistoryFacade) {
        this.paymentHistoryFacade = paymentHistoryFacade;
    }

    @GetMapping("payment-history/{childId}")
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<List<PaymentHistoryDto>> getById(@PathVariable UUID childId) {
        return new ResponseEntity<>(this.paymentHistoryFacade.getAllHistoryOfChild(childId), HttpStatus.OK);
    }

    @GetMapping("payments-history/month")
    @PreAuthorize(HAS_ROLE_USER)
    public ResponseEntity<List<PaymentHistoryDto>> getAllPaymentsHistoryFromMonth() {
        return new ResponseEntity<>(this.paymentHistoryFacade.getAllPaymentsHistoryFromMonth(), HttpStatus.OK);
    }

    @PostMapping("payment-history/balance-correction")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity applyBalanceCorrectionForPayment(@RequestBody PaymentHistoryDto paymentHistoryDto) {
        this.paymentHistoryFacade.applyBalanceCorrectionForPayment(paymentHistoryDto);
        return new ResponseEntity(HttpStatus.OK);
    }
}
