package pl.edu.pja.prz.core.controller.payments;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.payments.facade.DiscountFacade;
import pl.edu.pja.prz.payments.model.dto.DiscountDto;

import java.util.List;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;

@RestController
@RequestMapping(API_PAYMENTS)
public class DiscountController {
    private final DiscountFacade discountFacade;

    public DiscountController(DiscountFacade discountFacade) {
        this.discountFacade = discountFacade;
    }

    @GetMapping("discount/{id}")
    public ResponseEntity<DiscountDto> getById(@PathVariable Long id) {
        return new ResponseEntity<>(this.discountFacade.getById(id), HttpStatus.OK);
    }

    @GetMapping("discounts")
    public ResponseEntity<List<DiscountDto>> getAllDiscounts() {
        return new ResponseEntity<>(this.discountFacade.getAllDiscounts(), HttpStatus.OK);
    }

    @PostMapping("discount")
    public ResponseEntity<DiscountDto> createDiscount(@RequestBody DiscountDto discountDto) {
        return new ResponseEntity<>(this.discountFacade.createDiscount(discountDto), HttpStatus.OK);
    }

    @PutMapping("discount")
    public ResponseEntity<DiscountDto> updateDiscount(@RequestBody DiscountDto discountDto) {
        return new ResponseEntity<>(this.discountFacade.updateDiscount(discountDto), HttpStatus.OK);
    }

    @DeleteMapping("discount/{id}")
    public ResponseEntity deleteDiscount(@PathVariable Long id) {
        this.discountFacade.deleteDiscount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
