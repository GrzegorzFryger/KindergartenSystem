package pl.edu.pja.prz.payments.service;

import pl.edu.pja.prz.payments.model.Discount;

import java.util.List;

public interface DiscountService {
	Discount createDiscount(Discount discount);

	Discount updateDiscount(Discount discount);

	void deleteDiscount(Discount discount);

    void deleteDiscount(Long id);

	List<Discount> getAllDiscount();

	Discount getById(Long id);

	List<Discount> getByName(String name);
}
