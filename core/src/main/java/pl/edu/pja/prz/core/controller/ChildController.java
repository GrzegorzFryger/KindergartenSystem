package pl.edu.pja.prz.core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.facade.dto.ChildDto;

import java.util.UUID;

@RestController
@RequestMapping("api/account/")
public class ChildController {
	private final ChildFacade childFacade;

	public ChildController(ChildFacade childFacade) {
		this.childFacade = childFacade;
	}

	@PostMapping("child")
	public ResponseEntity<ChildDto> createChild(@RequestBody ChildDto accountDto) {
		return new ResponseEntity<>(childFacade.createChild(accountDto), HttpStatus.OK);
	}

	@GetMapping("child/{id}")
	public ResponseEntity<ChildDto> findChildById(@PathVariable UUID id) {
		return new ResponseEntity<>(childFacade.createChild(childFacade.findChildById(id)), HttpStatus.OK);
	}


}
