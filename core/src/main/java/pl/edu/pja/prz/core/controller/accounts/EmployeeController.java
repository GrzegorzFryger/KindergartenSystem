package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.EmployeeFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class EmployeeController {
    private final EmployeeFacade employeeFacade;

    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @PreAuthorize(HAS_ROLE_USER)
    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable UUID id) {
        return new ResponseEntity<>(employeeFacade.findById(id), HttpStatus.OK);
    }

    @GetMapping("employees")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        return new ResponseEntity<>(employeeFacade.findAll(), HttpStatus.OK);
    }

    @GetMapping("employees/count")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<Long> countEmployee() {
        return new ResponseEntity<>(employeeFacade.countEmployee(), HttpStatus.OK);
    }

    @PostMapping("employee")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(employeeFacade.createEmployee(accountDto), HttpStatus.OK);
    }

    @PutMapping("employee")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeFacade.updateEmployee(employeeDto), HttpStatus.OK);
    }

    @PostMapping("employeeAdmin")
    @PreAuthorize(HAS_ROLE_ADMIN)
    public ResponseEntity<EmployeeDto> createAdministratorAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(employeeFacade.createAdministratorAccount(accountDto), HttpStatus.OK);
    }

}
