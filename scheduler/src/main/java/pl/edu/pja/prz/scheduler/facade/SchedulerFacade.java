package pl.edu.pja.prz.scheduler.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;
import pl.edu.pja.prz.scheduler.facade.mapper.SchedulerMapper;
import pl.edu.pja.prz.scheduler.service.SchedulerService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulerFacade {
	private final SchedulerService schedulerService;
	private final SchedulerMapper schedulerMapper;

	@Autowired
	public SchedulerFacade(SchedulerService schedulerService, SchedulerMapper schedulerMapper) {
		this.schedulerService = schedulerService;
		this.schedulerMapper = schedulerMapper;
	}

	public List<JobInfoDto> findAvailableJobs() {
		return this.schedulerService.getAllDetailsAvailableJobs()
				.stream()
				.map(this.schedulerMapper::fromJobInfo)
				.collect(Collectors.toList());
	}

	public List<ScheduleJobInfoDto> findActiveJobs() {
		return this.schedulerService.getAllActiveScheduleJobs()
				.stream()
				.map(this.schedulerMapper::fromScheduleJobInfo)
				.collect(Collectors.toList());
	}

	public List<ScheduleJobInfoDto> findActiveJobsByGroupName(String groupName) {
		return this.schedulerService.getAllActiveScheduleJobsByGroupName(groupName)
				.stream()
				.map(this.schedulerMapper::fromScheduleJobInfo)
				.collect(Collectors.toList());
	}


	public ScheduleJobInfoDto scheduleCronJob(CronScheduleDto scheduleDto) {
		return schedulerMapper.fromScheduleJobInfo(
				this.schedulerService.scheduleCronJob(scheduleDto.getJobName(),
						scheduleDto.getTriggerDescription(),
						scheduleDto.getCronExpression(),
						scheduleDto.isDurability(),
						scheduleDto.getGroupName(),
						scheduleDto.getDataForJob()
				));
	}

	public void unScheduleAllJobs() {
		this.schedulerService.unScheduleAllJobs();
	}

	public void unScheduleAllJobsByGroupName(String groupName) {
		this.schedulerService.unScheduleAllJobsByGroup(groupName);
	}

	public void startJob(String jobKey) {
		this.schedulerService.startJob(jobKey);
	}

	public void pauseJob(String jobKey) {
		this.schedulerService.pauseJob(jobKey);
	}

	public void resumeJob(String jobKey) {
		this.schedulerService.resumeJob(jobKey);
	}

	public void removeJob(String jobKey) {
		this.schedulerService.removeJob(jobKey);
	}


}
