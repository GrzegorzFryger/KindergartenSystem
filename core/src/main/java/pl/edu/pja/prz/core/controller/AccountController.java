package pl.edu.pja.prz.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.account.facade.AdministratorAccountFacade;

@RestController
@RequestMapping("api/accounts/")
public class AccountController {
	private final AdministratorAccountFacade administratorAccountFacade;

	public AccountController(AdministratorAccountFacade administratorAccountFacade) {
		this.administratorAccountFacade = administratorAccountFacade;
	}

}
