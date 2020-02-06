package pl.edu.pja.prz.scheduler.facade.dto;

public class CronScheduleDto {
	private String jobName;
	private String triggerDescription;
	private String cronExpression;
	private boolean durability;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CronScheduleDto)) return false;

		CronScheduleDto that = (CronScheduleDto) o;

		if (isDurability() != that.isDurability()) return false;
		if (getJobName() != null ? !getJobName().equals(that.getJobName()) : that.getJobName() != null) return false;
		if (getTriggerDescription() != null ? !getTriggerDescription().equals(that.getTriggerDescription()) : that.getTriggerDescription() != null)
			return false;
		return getCronExpression() != null ? getCronExpression().equals(that.getCronExpression()) : that.getCronExpression() == null;
	}

	@Override
	public int hashCode() {
		int result = getJobName() != null ? getJobName().hashCode() : 0;
		result = 31 * result + (getTriggerDescription() != null ? getTriggerDescription().hashCode() : 0);
		result = 31 * result + (getCronExpression() != null ? getCronExpression().hashCode() : 0);
		result = 31 * result + (isDurability() ? 1 : 0);
		return result;
	}

	@Override
	public String toString() {
		return "CronScheduleDto{" +
				"jobName='" + jobName + '\'' +
				", triggerDescription='" + triggerDescription + '\'' +
				", cronExpression='" + cronExpression + '\'' +
				", durability=" + durability +
				'}';
	}
}
