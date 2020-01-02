package pl.edu.pja.prz.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.service.BoroughService;

@RestController
@RequestMapping("api/account/")
public class BoroughController {
	private final BoroughService boroughService;

	@Autowired
	public BoroughController(BoroughService boroughService) {
		this.boroughService = boroughService;
	}

	@GetMapping("borough/{id}")
	public ResponseEntity<Borough> findGuardianById(@PathVariable Long id) {
		return new ResponseEntity<>(boroughService.find(id).get(), HttpStatus.OK);
	}

	@PostMapping("borough")
	public ResponseEntity<Borough> createGuardian(@RequestBody Borough borough) {
		return new ResponseEntity<>(boroughService.create(borough), HttpStatus.OK);
	}

}
