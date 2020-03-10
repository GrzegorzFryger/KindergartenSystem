package pl.edu.pja.prz.scheduler.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.pja.prz.scheduler.facade.dto.CronScheduleDto;
import pl.edu.pja.prz.scheduler.facade.dto.JobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.ScheduleJobInfoDto;
import pl.edu.pja.prz.scheduler.facade.dto.SimpleScheduleDto;
import pl.edu.pja.prz.scheduler.facade.mapper.SchedulerMapper;
import pl.edu.pja.prz.scheduler.service.SchedulerService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SchedulerFacadeImpl implements SchedulerFacade {
	private final SchedulerService schedulerService;
	private final SchedulerMapper schedulerMapper;

	@Autowired
	public SchedulerFacadeImpl(SchedulerService schedulerService, SchedulerMapper schedulerMapper) {
		this.schedulerService = schedulerService;
		this.schedulerMapper = schedulerMapper;
	}

	@Override
	public List<JobInfoDto> findAvailableJobs() {
		return this.schedulerService.getAllDetailsAvailableJobs()
				.stream()
				.map(this.schedulerMapper::fromJobInfo)
				.collect(Collectors.toList());
	}

	@Override
	public List<ScheduleJobInfoDto> findActiveJobs() {
		return this.schedulerService.getAllActiveScheduleJobs()
				.stream()
				.map(this.schedulerMapper::fromScheduleJobInfo)
				.collect(Collectors.toList());
	}

	@Override
	public List<ScheduleJobInfoDto> findActiveJobsByGroupName(String groupName) {
		return this.schedulerService.getAllActiveScheduleJobsByGroupName(groupName)
				.stream()
				.map(this.schedulerMapper::fromScheduleJobInfo)
				.collect(Collectors.toList());
	}


	@Override
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

	@Override
	public ScheduleJobInfoDto scheduleSimpleJob(SimpleScheduleDto scheduleDto) {
		return schedulerMapper.fromScheduleJobInfo(
				this.schedulerService.scheduleSimpleJob(
						scheduleDto.getJobName(),
						scheduleDto.getTriggerDescription(),
						scheduleDto.getStartDate(),
						scheduleDto.getRepeatCount(),
						scheduleDto.getGroupName(),
						scheduleDto.getDataForJob()
				));
	}

	@Override
	public void unScheduleAllJobs() {
		this.schedulerService.unScheduleAllJobs();
	}

	@Override
	public void unScheduleAllJobsByGroupName(String groupName) {
		this.schedulerService.unScheduleAllJobsByGroup(groupName);
	}

	@Override
	public boolean startJob(String jobKey) {
		return this.schedulerService.startJob(jobKey);
	}

	@Override
	public boolean pauseJob(String jobKey) {
		return this.schedulerService.pauseJob(jobKey);
	}

	@Override
	public boolean resumeJob(String jobKey) {
		return this.schedulerService.resumeJob(jobKey);
	}

	@Override
	public boolean removeJob(String jobKey) {
		return this.schedulerService.removeJob(jobKey);
	}


}
