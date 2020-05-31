package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.dto.GuardianChildAssociationDto;
import pl.edu.pja.prz.account.model.dto.GuardianDto;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class GuardianController {
	private final GuardianFacade guardianFacade;

	@Autowired
	public GuardianController(GuardianFacade guardianFacade) {
		this.guardianFacade = guardianFacade;
	}

	@PreAuthorize(HAS_ROLE_USER)
	@GetMapping("guardian/{id}")
	public ResponseEntity<GuardianDto> findGuardianById(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findGuardianById(id), HttpStatus.OK);
	}

	@GetMapping("guardians")
	@PreAuthorize(HAS_ROLE_ADMIN)
	public ResponseEntity<List<GuardianDto>> findAllGuardians() {
		return new ResponseEntity<>(guardianFacade.findAllGuardians(), HttpStatus.OK);
	}

	@GetMapping("guardians/{id}/children")
	@PreAuthorize(HAS_ROLE_USER)
	public ResponseEntity<Set<ChildDto>> findAllGuardianChildren(@PathVariable UUID id) {
		return new ResponseEntity<>(guardianFacade.findAllGuardianChildren(id), HttpStatus.OK);

	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@GetMapping("guardians/search")
	public ResponseEntity<List<GuardianDto>> searchGuardianByFullName(@RequestParam String name, @RequestParam String surname) {

		return new ResponseEntity<>(guardianFacade.searchByFullName(new FullName(name, surname)), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@GetMapping("guardians/search/{childId}")
	public ResponseEntity<List<GuardianDto>> findAllGuardians(@PathVariable UUID childId) {
		return new ResponseEntity<>(guardianFacade.findByChildId(childId), HttpStatus.OK);
	}

	@GetMapping("guardians/count")
	@PreAuthorize(HAS_ROLE_ADMIN)
	public ResponseEntity<Long> countEmployee() {
		return new ResponseEntity<>(guardianFacade.countGuardian(), HttpStatus.OK);
	}

	@PostMapping("guardian")
	@PreAuthorize(HAS_ROLE_ADMIN)
	public ResponseEntity<GuardianDto> createGuardian(@RequestBody AccountDto accountDto) {
		GuardianDto guardianDto = guardianFacade.createGuardian(accountDto);
		return new ResponseEntity<>(guardianDto, HttpStatus.OK);
	}

	@PutMapping("guardian")
	@PreAuthorize(HAS_ROLE_ADMIN)
	public ResponseEntity<GuardianDto> updateGuardian(@RequestBody GuardianDto guardianDto) {
		GuardianDto guardian = guardianFacade.updateGuardian(guardianDto);
		return new ResponseEntity<>(guardian, HttpStatus.OK);
	}

	@PostMapping("guardian/append-child")
	@PreAuthorize(HAS_ROLE_ADMIN)
	public ResponseEntity<List<GuardianDto>> appendGuardian(@RequestBody GuardianChildAssociationDto associationDto) {
		var guardian = this.guardianFacade.appendGuardianToChild(associationDto);

		return new ResponseEntity<>(guardian, HttpStatus.OK);
	}
}
