package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
@CrossOrigin(origins = "*")
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

	@GetMapping("guardians/search")
	public ResponseEntity<List<GuardianDto>> searchGuardianByFullName(@RequestParam String name, @RequestParam String surname) {

		return new ResponseEntity<>(guardianFacade.searchByFullName(new FullName(name, surname)), HttpStatus.OK);
	}

	@GetMapping("guardians/search/{childId}")
	public ResponseEntity<List<GuardianDto>> findAllGuardians(@PathVariable UUID childId) {
		return new ResponseEntity<>(guardianFacade.findByChildId(childId), HttpStatus.OK);
	}

	@GetMapping("guardians/count")
	public ResponseEntity<Long> countEmployee() {
		return new ResponseEntity<>(guardianFacade.countGuardian(), HttpStatus.OK);
	}

	@PostMapping("guardian")
	public ResponseEntity<GuardianDto> createGuardian(@RequestBody AccountDto accountDto) {
		GuardianDto guardianDto = guardianFacade.createGuardian(accountDto);
		return new ResponseEntity<>(guardianDto, HttpStatus.OK);
	}

	@PutMapping("guardian")
	public ResponseEntity<GuardianDto> updateGuardian(@RequestBody GuardianDto guardianDto) {
		GuardianDto guardian = guardianFacade.updateGuardian(guardianDto);
		return new ResponseEntity<>(guardian, HttpStatus.OK);
	}

	@PostMapping("guardian/append-child")
	public ResponseEntity<GuardianDto> createGuardian(@RequestBody GuardianChildAssociationDto associationDto) {
		var guardian = this.guardianFacade.appendGuardianToChild(associationDto);

		var success = guardian.getChildren()
				.stream()
				.anyMatch(childDto -> childDto.getId().equals(associationDto.getChildId()));

		return success ? new ResponseEntity<>(guardian, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
