package pl.edu.pja.prz.core.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.calendar.facade.AbsenceFacade;
import pl.edu.pja.prz.calendar.facade.dto.AbsenceDto;

@RestController
@RequestMapping("api/absence/")
public class AbsenceController {
	private final AbsenceFacade absenceFacade;

	@Autowired
	public AbsenceController(AbsenceFacade absenceFacade) {
		this.absenceFacade = absenceFacade;
	}

	@GetMapping("{id}")
	public ResponseEntity<AbsenceDto> findAbsence(@PathVariable Long id) {
		return new ResponseEntity<>(absenceFacade.getAbsence(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<AbsenceDto> createAbsence(@RequestBody AbsenceDto absenceDto) {
		return new ResponseEntity<>(absenceFacade.createAbsence(absenceDto), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<AbsenceDto> updateAbsence(@RequestBody AbsenceDto absenceDto) {
		return new ResponseEntity<>(absenceFacade.updateAbsence(absenceDto), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteAbsence(@PathVariable Long id) {
		absenceFacade.deleteAbsence(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
