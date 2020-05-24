package pl.edu.pja.prz.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pja.prz.payments.model.Discount;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

    List<Discount> findAllByName(String name);

}
