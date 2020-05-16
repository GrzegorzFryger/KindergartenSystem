package pl.edu.pja.prz.core.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.payments.facade.RecurringPaymentFacade;
import pl.edu.pja.prz.payments.model.dto.RecurringPaymentDto;

import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;

@RestController
@RequestMapping(API_PAYMENTS)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class PaymentsController {
	private final RecurringPaymentFacade paymentFacade;
	public PaymentsController(RecurringPaymentFacade paymentFacade) {
		this.paymentFacade = paymentFacade;
	}

    @GetMapping("recurring-payments")
    public ResponseEntity<List<RecurringPaymentDto>> findAllRecurringPayments() {
		return new ResponseEntity<>(paymentFacade.findAllPayments(), HttpStatus.OK);
	}

    @GetMapping("recurring-payments/{childId}")
    public ResponseEntity<List<RecurringPaymentDto>> findAllRecurringPaymentsByChildId(@PathVariable UUID childId) {
        return new ResponseEntity<>(paymentFacade.findAllByChild(childId), HttpStatus.OK);
    }

    @GetMapping("recurring-payment/{id}")
	public ResponseEntity<RecurringPaymentDto> findPaymentById(@PathVariable Long id) {
		return new ResponseEntity<>(paymentFacade.findById(id), HttpStatus.OK);
	}

	@PostMapping("recurring-payment/tuition")
	public ResponseEntity<RecurringPaymentDto> createTuition(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.createTuition(recurringPaymentDto), HttpStatus.OK);
	}

    @PostMapping("recurring-payments/other")
	public ResponseEntity<RecurringPaymentDto> createOther(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.createOtherPayment(recurringPaymentDto), HttpStatus.OK);
	}

    @PutMapping("recurring-payments")
	public ResponseEntity<RecurringPaymentDto> updatePayment(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.updatePayment(recurringPaymentDto), HttpStatus.OK);
	}

    @DeleteMapping("recurring-payments/{id}")
	public ResponseEntity<?> deletePaymentById(@PathVariable Long id) {
		paymentFacade.deletePayment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

    @PutMapping("recurring-payments/cancel/{id}")
    public ResponseEntity<?> markAsCancelPayment(@PathVariable Long id) {
		return new ResponseEntity<>(paymentFacade.markAsCancelPayment(id), HttpStatus.OK);
	}

}
