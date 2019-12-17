package pl.edu.pja.prz.payments.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.payments.model.Discount;

public interface DiscountRepository extends CrudRepository<Discount,Long> {

}
