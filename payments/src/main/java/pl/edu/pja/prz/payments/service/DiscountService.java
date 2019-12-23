package pl.edu.pja.prz.payments.service;

import pl.edu.pja.prz.payments.model.Discount;

public interface DiscountService {
	Discount createDiscount(Discount discount);

	Discount updateDiscount(Long id, Discount discount);

	void deleteDiscount(Discount discount);
}
