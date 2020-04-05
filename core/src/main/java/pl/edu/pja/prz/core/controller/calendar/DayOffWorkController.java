package pl.edu.pja.prz.core.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.calendar.facade.DayOffWorkFacade;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;

import java.util.List;

import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_USER;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_CALENDAR;

@RestController
@RequestMapping(API_CALENDAR)
public class DayOffWorkController {
	private final DayOffWorkFacade dayOffWorkFacade;

	@Autowired
	public DayOffWorkController(DayOffWorkFacade dayOffWorkFacade) {
		this.dayOffWorkFacade = dayOffWorkFacade;
	}

	@PreAuthorize(HAS_ROLE_USER)
	@GetMapping("dayoff/{id}")
	public ResponseEntity<DayOffWorkDto> findDayOffWork(@PathVariable Long id) {
		return new ResponseEntity<>(dayOffWorkFacade.getDayOffWork(id), HttpStatus.OK);
	}

	@PreAuthorize(HAS_ROLE_USER)
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

	@PreAuthorize(HAS_ROLE_ADMIN)
	@PostMapping("dayoff/weekends/{year}")
	public ResponseEntity<List<DayOffWorkDto>> createDaysOffOnWeekends(@PathVariable int year) {
		return new ResponseEntity<>(dayOffWorkFacade.createDaysOffOnWeekends(year), HttpStatus.OK);
	}
}
