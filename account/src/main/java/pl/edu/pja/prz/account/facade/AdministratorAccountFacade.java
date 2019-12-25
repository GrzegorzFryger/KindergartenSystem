package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.service.AdministratorService;

@Service
public class AdministratorAccountFacade {
	private final AdministratorService administratorService;

	@Autowired
	public AdministratorAccountFacade(AdministratorService administratorService) {
		this.administratorService = administratorService;
	}

	


}
