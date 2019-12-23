package pl.edu.pja.prz.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.repository.DiscountRepository;

import java.util.List;

@Component
public class DiscountServiceImpl implements DiscountService {
	private final DiscountRepository discountRepository;

	@Autowired
	public DiscountServiceImpl(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override public Discount createDiscount(Discount discount) {
		return discountRepository.save(discount);
	}

	@Override public Discount updateDiscount(Long id, Discount discount) {
		return discountRepository.findById(id)
				.map(discount1 -> {
					discount1 = discount;
					return discountRepository.save(discount1);
				}).orElseThrow(() -> new IllegalArgumentException("Discount not found"));
	}

	@Override public void deleteDiscount(Discount discount) {
		discountRepository.delete(discount);
	}

	@Override public List<Discount> getAllDiscount() {
		return discountRepository.findAll();
	}

}
