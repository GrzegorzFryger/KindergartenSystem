package pl.edu.pja.prz.payments.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.PaymentFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.Status;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.FullName;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;
import pl.edu.pja.prz.payments.repository.DiscountRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecurringPaymentServiceImplTest {
	@Mock
	private RecurringPaymentRepository recurringPaymentRepository;
	@Mock
	private DiscountRepository discountRepository;
	@Mock
	private RecurringPayment mockPayment;


	private RecurringPaymentServiceImpl recurringPaymentService;
	private Child child;
	private FullName fullName;
	private Payment payment;
	private PeriodValidity periodValidity;

	@BeforeEach
	void setUp() {
		recurringPaymentService = new RecurringPaymentServiceImpl(recurringPaymentRepository, discountRepository);

		fullName = new FullName("Test Name", "Test surname");
		child = new Child(new UUID(1L, 2L), fullName, new UUID(3L, 4L));
		payment = new Payment(new BigDecimal("50.0"), "Test payment");
		periodValidity = new PeriodValidity(LocalDate.now(), LocalDate.now());
	}

	@Test
	void should_createOtherPayment() {
		//given
		var otherRecurringPayment = PaymentFactory.createOtherRecurringPayment(child, payment, periodValidity);
		//when
		when(recurringPaymentRepository.save(any(RecurringPayment.class))).thenReturn(otherRecurringPayment);
		var returnedTuition = recurringPaymentService.createOtherPayment(child, payment, periodValidity);
		//then
		verify(recurringPaymentRepository, times(1)).save(otherRecurringPayment);
		assertEquals(otherRecurringPayment, returnedTuition);
	}

	@Test
	void should_createTuition() {
		//given
		var tuition = PaymentFactory.createTuitionPayment(child, payment, periodValidity);
		//when
		when(recurringPaymentRepository.save(any(RecurringPayment.class))).thenReturn(tuition);
		var returnedTuition = recurringPaymentService.createTuition(child, payment, periodValidity);
		//then
		verify(recurringPaymentRepository, times(1)).save(tuition);
		assertEquals(tuition, returnedTuition);
	}

	@Test
	void should_throwException_when_updatePayment() {
		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.empty());
		//then
		assertThrows(IllegalArgumentException.class,
				() -> recurringPaymentService.updatePayment(1L, payment, periodValidity, Status.ACTIVE));
	}

	@Test
	void should_updatePayment() {
		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.of(mockPayment));
		when(recurringPaymentRepository.save(any())).thenReturn(mockPayment);

		recurringPaymentService.updatePayment(1L, payment, periodValidity, Status.ACTIVE);

		//then
		verify(recurringPaymentRepository, times(1)).save(mockPayment);
		verify(recurringPaymentRepository, times(1)).findById(1L);
		verify(mockPayment, times(1)).setAmount(any());
		verify(mockPayment, times(1)).setDescription(any());
		verify(mockPayment, times(1)).setPeriodValidity(any());
		verify(mockPayment, times(1)).setStatus(any());
	}

	@Test
	void should_markAsCancelPayment() {
		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.of(mockPayment));
		recurringPaymentService.markAsCancelPayment(1L);

		//then
		verify(recurringPaymentRepository, times(1)).save(mockPayment);
		verify(recurringPaymentRepository, times(1)).findById(1L);
		verify(mockPayment, times(1)).setStatus(Status.CANCELED);
	}

	@Test
	void deletePayment() {
		//when
		recurringPaymentService.deletePayment(mockPayment);

		//then
		verify(recurringPaymentRepository, times(1)).delete(mockPayment);

	}

	@Test
	void addDiscountsToPayment() {
	}

	@Test
	void removeDiscountsFromPayment() {
	}
}