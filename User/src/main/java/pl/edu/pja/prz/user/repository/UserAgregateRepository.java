package pl.edu.pja.prz.user.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.pja.prz.user.domain.UserAggregate;

import java.util.UUID;

public interface UserAgregateRepository extends CrudRepository<UUID, UserAggregate> {
}
