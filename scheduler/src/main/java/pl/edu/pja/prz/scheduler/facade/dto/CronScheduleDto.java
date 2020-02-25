package pl.edu.pja.prz.scheduler.facade.dto;

import java.util.Map;

public class CronScheduleDto {
	private String jobName;
	private String groupName;
	private String triggerDescription;
	private String cronExpression;
	private boolean durability;
	private Map<String, ?> dataForJob;

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

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public boolean isDurability() {
		return durability;
	}

	public void setDurability(boolean durability) {
		this.durability = durability;
	}

	public Map<String, ?> getDataForJob() {
		return dataForJob;
	}

	public void setDataForJob(Map<String, ?> dataForJob) {
		this.dataForJob = dataForJob;
	}

	@Override
	public String toString() {
		return "CronScheduleDto{" +
				"jobName='" + jobName + '\'' +
				", groupName='" + groupName + '\'' +
				", triggerDescription='" + triggerDescription + '\'' +
				", cronExpression='" + cronExpression + '\'' +
				", durability=" + durability +
				", dataForJob=" + dataForJob +
				'}';
	}
}
