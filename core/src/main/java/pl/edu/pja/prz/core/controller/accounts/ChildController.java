package pl.edu.pja.prz.core.controller.accounts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;

import java.util.UUID;

import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;

@RestController
@RequestMapping(API_ACCOUNT)
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class ChildController {
	private final ChildFacade childFacade;

	public ChildController(ChildFacade childFacade) {
		this.childFacade = childFacade;
	}

	@GetMapping("child/{id}")
	public ResponseEntity<ChildDto> findChildById(@PathVariable UUID id) {
		return new ResponseEntity<>(childFacade.findChildById(id), HttpStatus.OK);
	}

	@PutMapping("child")
	public ResponseEntity<ChildDto> updateChild(@RequestBody ChildDto childDto) {
		return new ResponseEntity<>(childFacade.updateChild(childDto), HttpStatus.OK);
	}

	@PostMapping("child")
	public ResponseEntity<ChildDto> createChild(@RequestBody ChildDto childDto) {
		return new ResponseEntity<>(childFacade.createChild(childDto), HttpStatus.OK);
	}


}
