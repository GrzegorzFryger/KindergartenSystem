package pl.edu.pja.prz.groups.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.groups.model.Group;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(value = "SELECT * FROM classrooms.classroom as g " +
            "INNER JOIN classrooms.classroom_child gc " +
            "ON g.id = gc.group_id " +
            "WHERE gc.child_id = :childId", nativeQuery = true)
    List<Group> getAllGroupsAssignedToChild(String childId);
}
