package pl.edu.pja.prz.core.controller.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.calendar.facade.DayOffWorkFacade;
import pl.edu.pja.prz.calendar.facade.dto.DayOffWorkDto;

import java.util.List;

@RestController
@RequestMapping("api/dayoff/")
public class DayOffWorkController {
	private final DayOffWorkFacade dayOffWorkFacade;

	@Autowired
	public DayOffWorkController(DayOffWorkFacade dayOffWorkFacade) {
		this.dayOffWorkFacade = dayOffWorkFacade;
	}

	@GetMapping("{id}")
	public ResponseEntity<DayOffWorkDto> findDayOffWork(@PathVariable Long id) {
		return new ResponseEntity<>(dayOffWorkFacade.getDayOffWork(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<DayOffWorkDto>> findAllDaysOffWork() {
		return new ResponseEntity<>(dayOffWorkFacade.getAllDaysOff(), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<DayOffWorkDto> createDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.createDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<DayOffWorkDto> updateDayOffWork(@RequestBody DayOffWorkDto dayOffWorkDto) {
		return new ResponseEntity<>(dayOffWorkFacade.updateDayOffWork(dayOffWorkDto), HttpStatus.OK);
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteDayOffWork(@PathVariable Long id) {
		dayOffWorkFacade.deleteDayOffWork(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
