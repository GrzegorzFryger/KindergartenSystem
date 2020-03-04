package pl.edu.pja.prz.core.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.calendar.facade.DayOffWorkFacade;
import pl.edu.pja.prz.calendar.model.dto.DayOffWorkDto;

import java.util.List;

@RestController
@RequestMapping("api/calendar/")
public class DayOffWorkController {
	private final DayOffWorkFacade dayOffWorkFacade;

	@Autowired
	public DayOffWorkController(DayOffWorkFacade dayOffWorkFacade) {
		this.dayOffWorkFacade = dayOffWorkFacade;
	}

	@GetMapping("dayoff/{id}")
	public ResponseEntity<DayOffWorkDto> findDayOffWork(@PathVariable Long id) {
		return new ResponseEntity<>(dayOffWorkFacade.getDayOffWork(id), HttpStatus.OK);
	}

	@GetMapping("daysoff")
	public ResponseEntity<List<DayOffWorkDto>> findAllDaysOffWork() {
		return new ResponseEntity<>(dayOffWorkFacade.getAllDaysOff(), HttpStatus.OK);
	}

	@PostMapping("dayoff")
	public ResponseEntity<DayOffWorkDto> createDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.createDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@PutMapping("dayoff")
	public ResponseEntity<DayOffWorkDto> updateDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.updateDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@DeleteMapping("dayoff/{id}")
	public ResponseEntity<?> deleteDayOffWork(@PathVariable Long id) {
		dayOffWorkFacade.deleteDayOffWork(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
