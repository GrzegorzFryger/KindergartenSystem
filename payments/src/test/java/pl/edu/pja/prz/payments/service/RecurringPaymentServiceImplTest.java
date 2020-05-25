package pl.edu.pja.prz.payments.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.Payment;
import pl.edu.pja.prz.payments.model.PaymentFactory;
import pl.edu.pja.prz.payments.model.RecurringPayment;
import pl.edu.pja.prz.payments.model.enums.StatusPayment;
import pl.edu.pja.prz.payments.model.enums.TypeDiscount;
import pl.edu.pja.prz.payments.model.value.Child;
import pl.edu.pja.prz.payments.model.value.PeriodValidity;
import pl.edu.pja.prz.payments.repository.DiscountRepository;
import pl.edu.pja.prz.payments.repository.RecurringPaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
	private Payment payment;
	private PeriodValidity periodValidity;

	@BeforeEach
	void setUp() {
		recurringPaymentService = new RecurringPaymentServiceImpl(recurringPaymentRepository, discountRepository);

		child = new Child(UUID.randomUUID(), UUID.randomUUID());
		payment = new Payment(new BigDecimal("50.0"), "Test payment");
		periodValidity = new PeriodValidity(LocalDate.now(), LocalDate.now());
	}

	@Test
	void should_createOtherPayment() {
		//given
		var childId = UUID.randomUUID();
		var guardianId = UUID.randomUUID();
		var otherRecurringPayment = PaymentFactory.createOtherRecurringPayment(childId, guardianId, payment, periodValidity);
		//when
		when(recurringPaymentRepository.save(any(RecurringPayment.class))).thenReturn(otherRecurringPayment);
		var returnedTuition = recurringPaymentService.createOtherPayment(childId, guardianId, payment, periodValidity);
		//then
		verify(recurringPaymentRepository, times(1)).save(otherRecurringPayment);
		assertEquals(otherRecurringPayment, returnedTuition);
	}

	@Test
	void should_createTuition() {
		//given
		var childId = UUID.randomUUID();
		var guardianId = UUID.randomUUID();
		var tuition = PaymentFactory.createTuitionPayment(childId, guardianId, payment, periodValidity);
		//when
		when(recurringPaymentRepository.save(any(RecurringPayment.class))).thenReturn(tuition);
		var returnedTuition = recurringPaymentService.createTuition(childId, guardianId, payment, periodValidity);
		//then
		verify(recurringPaymentRepository, times(1)).save(tuition);
		assertEquals(tuition, returnedTuition);
	}

	@Test
	void should_throwException_when_updatePayment() {
		//given
		var payment = new RecurringPayment();
		payment.setId(1L);
        payment.setStatusPayment(StatusPayment.ACTIVE);
		payment.setBaseAmount(new BigDecimal("50.0"));
		payment.setDescription( "Test payment");
		payment.setPeriodValidity(periodValidity);

		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.empty());
		//then
		assertThrows(IllegalArgumentException.class,
				() -> recurringPaymentService.updatePayment(payment));
	}

	@Test
	void should_updatePayment() {
		//given
		var payment = new RecurringPayment();
		payment.setId(1L);
        payment.setStatusPayment(StatusPayment.ACTIVE);
		payment.setBaseAmount(new BigDecimal("50.0"));
		payment.setDescription( "Test payment");
		payment.setPeriodValidity(periodValidity);
		payment.setDiscount(new Discount());

		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.of(mockPayment));
		when(recurringPaymentRepository.save(any())).thenReturn(mockPayment);

		recurringPaymentService.updatePayment(payment);

		//then
		verify(recurringPaymentRepository, times(1)).save(mockPayment);
		verify(recurringPaymentRepository, times(1)).findById(1L);
		verify(mockPayment, times(1)).setBaseAmount(any());
		verify(mockPayment, times(1)).setDescription(any());
		verify(mockPayment, times(1)).setPeriodValidity(any());
        verify(mockPayment, times(1)).setStatusPayment(any());
        verify(mockPayment, times(1)).setDiscount(any(Discount.class));
	}

	@Test
	void should_markAsCancelPayment() {
		//given
		var payment = new RecurringPayment();
		payment.setId(1L);
		payment.setBaseAmount(new BigDecimal("50.0"));
		payment.setDescription( "Test payment");
		payment.setPeriodValidity(periodValidity);

		//when
		when(recurringPaymentRepository.findById(1L)).thenReturn(Optional.of(payment));
		when(recurringPaymentRepository.save(any())).thenReturn(payment);
		recurringPaymentService.markAsCancelPayment(1L);

		//then
        assertEquals(StatusPayment.CANCELED, payment.getStatusPayment());
		verify(recurringPaymentRepository, times(1)).save(any());
		verify(recurringPaymentRepository, times(1)).findById(1L);

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
		//given
		var discount = new Discount("discount test", new BigDecimal("50.0"), TypeDiscount.AMOUNT);

		//when
		when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));
		when(recurringPaymentRepository.findActiveByChildId(any())).thenReturn(Optional.of(mockPayment));
		when(recurringPaymentRepository.save(any())).thenReturn(mockPayment);
		when(mockPayment.getDiscount()).thenReturn(discount);

		var result = recurringPaymentService
				.addDiscountToPayment(new UUID(1L, 2L), 1L);

		//then
		assertEquals(discount, result);
		verify(recurringPaymentRepository, times(1)).save(mockPayment);
		verify(recurringPaymentRepository, times(1)).findActiveByChildId(any());
		verify(mockPayment, times(1)).getDiscount();
		verify(mockPayment, times(1)).setDiscount(any());
		verify(discountRepository, times(1)).findById(1L);
	}

	@Test
	void removeDiscountsFromPayment() {
		//given
		var discount = new Discount("discount test", new BigDecimal("50.0"), TypeDiscount.AMOUNT);

		//when
		when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));
		when(recurringPaymentRepository.findActiveByChildId(any())).thenReturn(Optional.of(mockPayment));
		when(recurringPaymentRepository.save(any())).thenReturn(mockPayment);
		when(mockPayment.getDiscount()).thenReturn(discount);

		var result = recurringPaymentService
				.removeDiscountsFromPayment(new UUID(1L, 2L), 1L);

		//then
		assertEquals(discount, result);
		verify(recurringPaymentRepository, times(1)).save(mockPayment);
		verify(recurringPaymentRepository, times(1)).findActiveByChildId(any());
		verify(mockPayment, times(1)).getDiscount();
		verify(mockPayment, times(1)).setDiscount(any());
		verify(discountRepository, times(1)).findById(1L);
	}

	@Test
	public void shouldGetRecurringPaymentById() {
		//Given
		RecurringPayment recurringPayment = new RecurringPayment();

		//When
		when(recurringPaymentRepository.findById(anyLong())).thenReturn(Optional.of(recurringPayment));
		RecurringPayment result = recurringPaymentService.getPaymentById(1L);

		//Then
		assertNotNull(result);
		verify(recurringPaymentRepository, only()).findById(anyLong());
	}

	@Test
	public void shouldThrowErrorWhenRecurringPaymentByIdNotFound() {
		//Given

		//When
		when(recurringPaymentRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(IllegalArgumentException.class, () -> {
			RecurringPayment result = recurringPaymentService.getPaymentById(1L);
		});

		//Then
		verify(recurringPaymentRepository, only()).findById(anyLong());
	}

	@Test
	public void shouldGetAllRecurringPayments() {
		//Given
		List<RecurringPayment> recurringPaymentList = new ArrayList();
		recurringPaymentList.add(new RecurringPayment());

		//When
		when(recurringPaymentRepository.findAll()).thenReturn(recurringPaymentList);
		List<RecurringPayment> result = recurringPaymentService.getAllPayments();

		//Then
		assertNotNull(result);
		assertEquals(1, result.size());
		verify(recurringPaymentRepository, only()).findAll();
	}

	@Test
	public void shouldDeleteRecurringPayment() {
		//Given

		//When
		recurringPaymentService.deletePayment(1L);

		//Then
		verify(recurringPaymentRepository, only()).deleteById(anyLong());
	}

}