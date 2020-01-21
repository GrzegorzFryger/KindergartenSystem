package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.EmployeeFacade;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.EmployeeDto;

import java.util.UUID;

@RestController
@RequestMapping("api/account/")
public class EmployeeController {
    private final EmployeeFacade employeeFacade;

    public EmployeeController(EmployeeFacade employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    @GetMapping("employee/{id}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable UUID id) {
        return new ResponseEntity<>(employeeFacade.findById(id), HttpStatus.OK);
    }

    @PostMapping("employee")
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(employeeFacade.createEmployee(accountDto), HttpStatus.OK);
    }

    @PostMapping("employeeAdmin")
    public ResponseEntity<EmployeeDto> createAdministratorAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(employeeFacade.createAdministratorAccount(accountDto), HttpStatus.OK);
    }

}