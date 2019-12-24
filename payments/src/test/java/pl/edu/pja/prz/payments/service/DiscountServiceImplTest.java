package pl.edu.pja.prz.payments.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.model.enums.TypeDiscount;
import pl.edu.pja.prz.payments.repository.DiscountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiscountServiceImplTest {

	@Mock
	private DiscountRepository discountRepository;
	private DiscountService discountService;

	private Discount discount;

	@BeforeEach
	void setUp() {
		discountService = new DiscountServiceImpl(discountRepository);
		discount = new Discount("Test Discount",
				new BigDecimal(String.valueOf("50.0")),
				TypeDiscount.PERCENTAGE
		);
	}

	@Test
	void shouldCreateDiscount() {
		//when
		when(discountRepository.save(any(Discount.class))).thenReturn(discount);
		var createdDiscount = discountService.createDiscount(discount);
		//then
		verify(discountRepository, times(1)).save(any(Discount.class));
		assertEquals(discount, createdDiscount);
	}

	@Test
	void shouldUpdateDiscount() {
		//when
		when(discountRepository.findById(1L)).thenReturn(Optional.of(discount));
		when(discountRepository.save(any(Discount.class))).thenReturn(discount);
		var createdDiscount = discountService.updateDiscount(1L, discount);

		//then
		verify(discountRepository, times(1)).save(any(Discount.class));
		assertEquals(discount, createdDiscount);
	}

	@Test
	void shouldDeleteDiscount() {
		//when
		discountService.deleteDiscount(discount);

		//then
		verify(discountRepository, times(1)).delete(any(Discount.class));
	}

	@Test
	void shouldGetAllDiscount() {
		//when
		when(discountRepository.findAll()).thenReturn(List.of(discount));
		var createdDiscounts = discountService.getAllDiscount();

		//then
		verify(discountRepository, times(1)).findAll();
		assertEquals(1, createdDiscounts.size());
	}
}