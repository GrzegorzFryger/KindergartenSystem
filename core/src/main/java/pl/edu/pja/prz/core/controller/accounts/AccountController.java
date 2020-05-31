package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.AccountCredentialFacade;
import pl.edu.pja.prz.account.facade.AccountFacade;
import pl.edu.pja.prz.account.model.dto.AccountActivateDto;
import pl.edu.pja.prz.account.model.dto.AccountDto;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
public class AccountController {
	private final AccountFacade accountFacade;
	private final AccountCredentialFacade accountCredentialFacade;

	@Autowired
	public AccountController(AccountFacade accountFacade,
							 AccountCredentialFacade accountCredentialFacade) {
		this.accountFacade = accountFacade;
		this.accountCredentialFacade = accountCredentialFacade;
	}

	@PreAuthorize(HAS_ROLE_USER)
	@GetMapping("user")
	public ResponseEntity<AccountDto> findAccountByEmail(@RequestParam String email, Authentication authentication) {
		var account = accountFacade.findAccountByEmail(email);

		if (account.getEmail().equals(authentication.getName())) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("activate")
    public ResponseEntity<Boolean> activateAccount(@RequestBody AccountActivateDto accountActivateDto) {
		return new ResponseEntity<>(this.accountCredentialFacade.activateAccount(accountActivateDto), HttpStatus.OK );
	}


	@PutMapping()
    public ResponseEntity<AccountDto> updatePersonalData(@RequestBody AccountDto person) {
		return new ResponseEntity<>(accountFacade.updatePersonalData(person), HttpStatus.OK );
	}





}
