package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.payments.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount,Long> {

}
