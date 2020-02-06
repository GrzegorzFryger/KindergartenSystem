package pl.edu.pja.prz.core.controller.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pja.prz.scheduler.facade.SchedulerFacade;
import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;

import java.util.List;

@RestController
@RequestMapping("api/scheduler/")
public class SchedulerController {
	private final SchedulerFacade schedulerFacade;

	@Autowired
	public SchedulerController(SchedulerFacade schedulerFacade) {
		this.schedulerFacade = schedulerFacade;
	}

	@GetMapping("all")
	public ResponseEntity<List<JobInfoDto>> findAvailableJobs() {
		return new ResponseEntity<>(schedulerFacade.findAvailableJobs(), HttpStatus.OK);
	}

	@PutMapping("all")
	public ResponseEntity<?> unScheduleAllJobs() {
		schedulerFacade.unScheduleAllJobs();
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@GetMapping("active")
	public ResponseEntity<List<ScheduleJobInfoDto>> findActiveJobs() {
		return new ResponseEntity<>(schedulerFacade.findActiveJobs(), HttpStatus.OK);
	}

	@PostMapping("cron")
	public ResponseEntity<ScheduleJobInfoDto> scheduleCronJob(@RequestBody CronScheduleDto cronScheduleDto) {
		return new ResponseEntity<>(schedulerFacade.scheduleCronJob(cronScheduleDto), HttpStatus.OK);
	}

	@PutMapping("start/{jobKey}")
	public ResponseEntity<?> startJob(@PathVariable String jobKey ) {
		schedulerFacade.startJob(jobKey);
		return new ResponseEntity<>( HttpStatus.OK);
	}
	@PutMapping("pause/{jobKey}")
	public ResponseEntity<?> pauseJob(@PathVariable String jobKey ) {
		schedulerFacade.pauseJob(jobKey);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@PutMapping("resume/{jobKey}")
	public ResponseEntity<Object> resumeJob(@PathVariable String jobKey ) {
		schedulerFacade.resumeJob(jobKey);
		return new ResponseEntity<>( HttpStatus.OK);
	}

	@PutMapping("remove/{jobKey}")
	public ResponseEntity<?> removeJob(@PathVariable String jobKey ) {
		schedulerFacade.removeJob(jobKey);
		return new ResponseEntity<>( HttpStatus.OK);
	}

}
