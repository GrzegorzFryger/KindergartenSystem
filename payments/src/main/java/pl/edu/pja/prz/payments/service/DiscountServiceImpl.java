package pl.edu.pja.prz.payments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.payments.model.Discount;
import pl.edu.pja.prz.payments.repository.DiscountRepository;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
	private final DiscountRepository discountRepository;

	@Autowired
	public DiscountServiceImpl(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override
	public Discount createDiscount(Discount discount) {
		return discountRepository.save(discount);
	}

	@Override
	public Discount updateDiscount(Discount discount) {
		return discountRepository.findById(discount.getId())
				.map(discount1 -> {
					discount1 = discount;
					return discountRepository.save(discount1);
				}).orElseThrow(() -> new ElementNotFoundException(discount.getId()));
	}

	@Override
	public void deleteDiscount(Discount discount) {
		discountRepository.delete(discount);
	}

    @Override
    public void deleteDiscount(Long id) {
        discountRepository.deleteById(id);
    }

	@Override
	public List<Discount> getAllDiscount() {
		return discountRepository.findAll();
	}

	@Override
	public Discount getById(Long id) {
		return this.discountRepository.findById(id).orElseThrow(() -> new ElementNotFoundException(id));
	}

	@Override
	public List<Discount> getByName(String name) {
		return this.discountRepository.findAllByName(name);
	}

}
