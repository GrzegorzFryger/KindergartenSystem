package pl.edu.pja.prz.scheduler.facade.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class SimpleScheduleDto {
	private String jobName;
	private String groupName;
	private String triggerDescription;
	private LocalDateTime startDate;
	private int repeatCount;
	private Map<String, ?> dataForJob;

	public SimpleScheduleDto() {
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTriggerDescription() {
		return triggerDescription;
	}

	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public int getRepeatCount() {
		return repeatCount;
	}

	public void setRepeatCount(int repeatCount) {
		this.repeatCount = repeatCount;
	}

	public Map<String, ?> getDataForJob() {
		return dataForJob;
	}

	public void setDataForJob(Map<String, ?> dataForJob) {
		this.dataForJob = dataForJob;
	}
}
