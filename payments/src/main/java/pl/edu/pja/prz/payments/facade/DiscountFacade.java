package pl.edu.pja.prz.payments.facade;

import pl.edu.pja.prz.payments.model.dto.DiscountDto;

import java.util.List;

public interface DiscountFacade {
    DiscountDto getById(Long id);

    List<DiscountDto> getByName(String name);

    DiscountDto createDiscount(DiscountDto discountDto);

    DiscountDto updateDiscount(DiscountDto discountDto);

    void deleteDiscount(DiscountDto discountDto);

    List<DiscountDto> getAllDiscounts();
}
