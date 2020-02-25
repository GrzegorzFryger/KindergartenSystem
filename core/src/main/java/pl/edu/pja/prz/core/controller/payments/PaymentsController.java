package pl.edu.pja.prz.core.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.payments.facade.RecurringPaymentFacade;
import pl.edu.pja.prz.payments.facade.dto.RecurringPaymentDto;

import java.util.List;

@RestController
@RequestMapping("api/payments/")
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class PaymentsController {
	private final RecurringPaymentFacade paymentFacade;

	public PaymentsController(RecurringPaymentFacade paymentFacade) {
		this.paymentFacade = paymentFacade;
	}

	@GetMapping
	public ResponseEntity<List<RecurringPaymentDto>> findAllPayments() {
		return new ResponseEntity<>(paymentFacade.findAllPayments(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<RecurringPaymentDto> findPaymentById(@PathVariable Long id) {
		return new ResponseEntity<>(paymentFacade.findById(id), HttpStatus.OK);
	}

	@PostMapping("tuition")
	public ResponseEntity<RecurringPaymentDto> createTuition(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.createTuition(recurringPaymentDto), HttpStatus.OK);
	}

	@PostMapping("other")
	public ResponseEntity<RecurringPaymentDto> createOther(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.createOtherPayment(recurringPaymentDto), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<RecurringPaymentDto> updatePayment(@RequestBody RecurringPaymentDto recurringPaymentDto) {
		return new ResponseEntity<>(paymentFacade.updatePayment(recurringPaymentDto), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deletePaymentById(@PathVariable Long id) {
		paymentFacade.deletePayment(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("cancel")
	public ResponseEntity<?> markAsCancelPayment(@RequestBody Long id) {
		return new ResponseEntity<>(paymentFacade.markAsCancelPayment(id), HttpStatus.OK);
	}

}
