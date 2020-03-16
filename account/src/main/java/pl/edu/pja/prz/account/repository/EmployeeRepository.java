package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Employee;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends BasicAccountRepository<Employee, UUID> {
}
