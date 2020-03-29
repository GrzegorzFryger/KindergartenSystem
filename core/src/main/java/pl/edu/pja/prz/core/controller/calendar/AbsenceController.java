package pl.edu.pja.prz.core.controller.calendar;

import static pl.edu.pja.prz.commons.constants.Roles.*;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_CALENDAR;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.calendar.facade.AbsenceFacade;
import pl.edu.pja.prz.calendar.model.dto.AbsenceDto;

@RestController
@RequestMapping(API_CALENDAR)
public class AbsenceController {
	private final AbsenceFacade absenceFacade;

	@Autowired
	public AbsenceController(AbsenceFacade absenceFacade) {
		this.absenceFacade = absenceFacade;
	}

	@PreAuthorize(HAS_ANY_ROLE)
	@GetMapping("absence/{id}")
	public ResponseEntity<AbsenceDto> findAbsence(@PathVariable Long id) {
		return new ResponseEntity<>(absenceFacade.getAbsence(id), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@PostMapping("absence")
	public ResponseEntity<AbsenceDto> createAbsence(@RequestBody AbsenceDto absenceDto) {
		return new ResponseEntity<>(absenceFacade.createAbsence(absenceDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@PutMapping("absence")
	public ResponseEntity<AbsenceDto> updateAbsence(@RequestBody AbsenceDto absenceDto) {
		return new ResponseEntity<>(absenceFacade.updateAbsence(absenceDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@DeleteMapping("absence/{id}")
	public ResponseEntity<?> deleteAbsence(@PathVariable Long id) {
		absenceFacade.deleteAbsence(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@GetMapping("absence/childById/{childId}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesByChildId(@PathVariable UUID childId) {
		return new ResponseEntity<>(absenceFacade.getAllAbsencesByChildId(childId), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_TEACHER + OR + HAS_ROLE_ADMIN)
	@GetMapping("absence/childByDate/{date}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesByDate(@PathVariable String date) {
		LocalDate dateToCheck = LocalDate.parse(date);
		return new ResponseEntity<>(absenceFacade.getAllAbsencesByDate(dateToCheck), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ANY_ROLE)
	@GetMapping("absence/child/{childId}/{startDate}/{endDate}")
	public ResponseEntity<List<AbsenceDto>> getAllAbsencesForChildBetweenDates(@PathVariable UUID childId,
																			   @PathVariable String startDate,
																			   @PathVariable String endDate) {
		LocalDate dateFrom = LocalDate.parse(startDate);
		LocalDate dateTo = LocalDate.parse(endDate);
		return new ResponseEntity<>(absenceFacade.getAllAbsencesForChildBetweenDates(childId, dateFrom, dateTo), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_USER + OR + HAS_ROLE_ADMIN)
	@PostMapping("absence/childMultiple/{childId}/{startDate}/{endDate}/{reason}")
	public ResponseEntity<List<AbsenceDto>> createAbsencesForChildBetweenDates(@PathVariable UUID childId,
																			   @PathVariable String startDate,
																			   @PathVariable String endDate,
																			   @PathVariable String reason) {
		LocalDate dateFrom = LocalDate.parse(startDate);
		LocalDate dateTo = LocalDate.parse(endDate);
		return new ResponseEntity<>(absenceFacade.createAbsencesForChildBetweenDates(childId, dateFrom, dateTo, reason), HttpStatus.OK);
	}

}
