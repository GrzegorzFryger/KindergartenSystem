package pl.edu.pja.prz.core.controller.accounts;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.dto.GuardianDto;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(API_ACCOUNT)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class GuardianController {
	private final GuardianFacade guardianFacade;

	@Autowired
	public GuardianController(GuardianFacade guardianFacade) {
		this.guardianFacade = guardianFacade;
	}

	@GetMapping("guardian/{id}")
	public ResponseEntity<GuardianDto> findGuardianById(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findGuardianById(id), HttpStatus.OK);
	}

	@GetMapping("guardians")
	public ResponseEntity<List<GuardianDto>> findAllGuardians() {
		return new ResponseEntity<>(guardianFacade.findAllGuardians(), HttpStatus.OK);
	}

	@GetMapping("guardians/{id}/children")
	public ResponseEntity<Set<ChildDto>> findAllGuardianChildren(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findAllGuardianChildren(id), HttpStatus.OK);
	}

	@PostMapping("guardian")
	public ResponseEntity<GuardianDto> createGuardian(@RequestBody AccountDto accountDto) {
		return new ResponseEntity<>(guardianFacade.createGuardian(accountDto), HttpStatus.OK);
	}



}
