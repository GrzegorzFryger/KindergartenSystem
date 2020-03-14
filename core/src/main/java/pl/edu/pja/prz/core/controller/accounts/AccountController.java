package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.account.facade.AccountFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
public class AccountController {
	private final AccountFacade accountFacade;

	@Autowired
	public AccountController(AccountFacade accountFacade) {
		this.accountFacade = accountFacade;
	}

	@GetMapping("user")
	public ResponseEntity<AccountDto> findBorough(@RequestParam String email, Authentication authentication ) {
		var account = accountFacade.findAccountByEmail(email);

		if(account.getEmail().equals(authentication.getName())){
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);

	}
}
