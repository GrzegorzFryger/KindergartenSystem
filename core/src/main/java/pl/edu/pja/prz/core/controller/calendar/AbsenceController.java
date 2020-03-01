package pl.edu.pja.prz.core.controller.calendar;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.calendar.facade.AbsenceFacade;
import pl.edu.pja.prz.calendar.facade.dto.AbsenceDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

	@GetMapping("child/{childId}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesByChildId(@PathVariable UUID childId) {
		return new ResponseEntity<>(absenceFacade.getAllAbsencesByChildId(childId), HttpStatus.OK);
	}

	@GetMapping("child/{date}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesByDate(@PathVariable String date) {
		LocalDate dateToCheck = LocalDate.parse(date);
		return new ResponseEntity<>(absenceFacade.getAllAbsencesByDate(dateToCheck), HttpStatus.OK);
	}

	@GetMapping("child/{childId}/{startDate}/{endDate}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesForChildBetweenDates(@PathVariable UUID childId,
																			   @PathVariable String startDate,
																			   @PathVariable String endDate) {
		LocalDate dateFrom = LocalDate.parse(startDate);
		LocalDate dateTo = LocalDate.parse(endDate);
		return new ResponseEntity<>(absenceFacade.getAllAbsencesForChildBetweenDates(childId, dateFrom, dateTo), HttpStatus.OK);
	}

}
