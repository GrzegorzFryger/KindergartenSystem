package pl.edu.pja.prz.account.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.account.facade.mapper.PersonMapper;
import pl.edu.pja.prz.account.service.AdministratorService;

@Service
public class AdministratorAccountFacade {
	private final AdministratorService administratorService;
	private final PersonMapper personMapper;

	@Autowired
	public AdministratorAccountFacade(AdministratorService administratorService, PersonMapper personMapper) {
		this.administratorService = administratorService;
		this.personMapper = personMapper;
	}

}
