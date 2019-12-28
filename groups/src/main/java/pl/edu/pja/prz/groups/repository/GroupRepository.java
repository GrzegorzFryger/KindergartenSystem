package pl.edu.pja.prz.groups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.groups.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
