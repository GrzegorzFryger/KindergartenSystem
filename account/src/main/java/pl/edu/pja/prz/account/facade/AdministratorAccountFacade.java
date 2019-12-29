package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.mapper.EmployeeMapper;
import pl.edu.pja.prz.account.facade.mapper.GuardianMapper;
import pl.edu.pja.prz.account.service.AdministratorService;

@Service
public class AdministratorAccountFacade {
	private final AdministratorService administratorService;
	private final EmployeeMapper employeeMapper;
	private final GuardianMapper guardianMapper;

	@Autowired
	public AdministratorAccountFacade(AdministratorService administratorService, EmployeeMapper employeeMapper,
	                                  GuardianMapper guardianMapper) {
		this.administratorService = administratorService;
		this.employeeMapper = employeeMapper;
		this.guardianMapper = guardianMapper;
	}

}
