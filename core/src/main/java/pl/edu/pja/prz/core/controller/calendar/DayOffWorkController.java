package pl.edu.pja.prz.core.controller.calendar;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ANY_ROLE;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;

import java.util.List;
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
import pl.edu.pja.prz.calendar.facade.DayOffWorkFacade;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;

@RestController
@RequestMapping("api/calendar/")
public class DayOffWorkController {
	private final DayOffWorkFacade dayOffWorkFacade;

	@Autowired
	public DayOffWorkController(DayOffWorkFacade dayOffWorkFacade) {
		this.dayOffWorkFacade = dayOffWorkFacade;
	}

	@PreAuthorize(HAS_ANY_ROLE)
	@GetMapping("dayoff/{id}")
	public ResponseEntity<DayOffWorkDto> findDayOffWork(@PathVariable Long id) {
		return new ResponseEntity<>(dayOffWorkFacade.getDayOffWork(id), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ANY_ROLE)
	@GetMapping("daysoff")
	public ResponseEntity<List<DayOffWorkDto>> findAllDaysOffWork() {
		return new ResponseEntity<>(dayOffWorkFacade.getAllDaysOff(), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PostMapping("dayoff")
	public ResponseEntity<DayOffWorkDto> createDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.createDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PutMapping("dayoff")
	public ResponseEntity<DayOffWorkDto> updateDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.updateDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_ADMIN)
	@DeleteMapping("dayoff/{id}")
	public ResponseEntity<?> deleteDayOffWork(@PathVariable Long id) {
		dayOffWorkFacade.deleteDayOffWork(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
