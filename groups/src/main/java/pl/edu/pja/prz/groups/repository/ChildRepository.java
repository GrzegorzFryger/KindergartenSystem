package pl.edu.pja.prz.groups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.groups.model.Child;

import java.util.UUID;

@Repository
public interface ChildRepository extends JpaRepository<Child, UUID> {

}
