package pl.edu.pja.prz.account.repository;

import org.springframework.stereotype.Repository;
import pl.edu.pja.prz.account.model.Employee;

@Repository
public interface EmployeeRepository extends BasicAccountRepository<Employee> {
}
