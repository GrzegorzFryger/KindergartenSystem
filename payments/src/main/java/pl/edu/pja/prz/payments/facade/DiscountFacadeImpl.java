package pl.edu.pja.prz.payments.facade;

import org.springframework.stereotype.Component;
import pl.edu.pja.prz.payments.facade.mapper.DiscountMapper;
import pl.edu.pja.prz.payments.model.dto.DiscountDto;
import pl.edu.pja.prz.payments.service.DiscountService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountFacadeImpl implements DiscountFacade {
    private final DiscountService discountService;
    private final DiscountMapper discountMapper;

    public DiscountFacadeImpl(DiscountService discountService, DiscountMapper discountMapper) {
        this.discountService = discountService;
        this.discountMapper = discountMapper;
    }

    @Override
    public DiscountDto getById(Long id) {
        return this.discountMapper.fromDiscount(this.discountService.getById(id));
    }

    @Override
    public List<DiscountDto> getByName(String name) {
        return this.discountService.getByName(name)
                .stream()
                .map(this.discountMapper::fromDiscount)
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDto createDiscount(DiscountDto discountDto) {
        return this.discountMapper.fromDiscount(this.discountService.createDiscount(
                this.discountMapper.toDiscount(discountDto)
        ));
    }

    @Override
    public DiscountDto updateDiscount(DiscountDto discountDto) {
        return this.discountMapper.fromDiscount(this.discountService.updateDiscount(
                this.discountMapper.toDiscount(discountDto)
        ));
    }

    @Override
    public void deleteDiscount(DiscountDto discountDto) {
        this.discountMapper.fromDiscount(this.discountService.createDiscount(
                this.discountMapper.toDiscount(discountDto)
        ));
    }

    @Override
    public List<DiscountDto> getAllDiscounts() {
        return this.discountService.getAllDiscount()
                .stream()
                .map(this.discountMapper::fromDiscount)
                .collect(Collectors.toList());
    }

}

