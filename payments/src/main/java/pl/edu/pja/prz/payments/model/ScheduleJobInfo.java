package pl.edu.pja.prz.payments.model;

import java.util.Date;

public class ScheduleJobInfo {
	private String jobKey;
	private String jobDescription;
	private String isDurable;
	private String triggerKey;
	private String triggerDescription;
	private Date triggerStart;
	private Date triggerEnd;

	public ScheduleJobInfo() {
	}

	public ScheduleJobInfo(String jobKey, String jobDescription, String isDurable, String triggerKey,
	                       String triggerDescription, Date triggerStart, Date triggerEnd) {
		this.jobKey = jobKey;
		this.jobDescription = jobDescription;
		this.isDurable = isDurable;
		this.triggerKey = triggerKey;
		this.triggerDescription = triggerDescription;
		this.triggerStart = triggerStart;
		this.triggerEnd = triggerEnd;
	}

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getIsDurable() {
		return isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}

	public String getTriggerKey() {
		return triggerKey;
	}

	public void setTriggerKey(String triggerKey) {
		this.triggerKey = triggerKey;
	}

	public String getTriggerDescription() {
		return triggerDescription;
	}

	public void setTriggerDescription(String triggerDescription) {
		this.triggerDescription = triggerDescription;
	}

	public Date getTriggerStart() {
		return triggerStart;
	}

	public void setTriggerStart(Date triggerStart) {
		this.triggerStart = triggerStart;
	}

	public Date getTriggerEnd() {
		return triggerEnd;
	}

	public void setTriggerEnd(Date triggerEnd) {
		this.triggerEnd = triggerEnd;
	}

	@Override
	public String toString() {
		return "ScheduleJobInfo{" +
				"jobKey='" + jobKey + '\'' +
				", jobDescription='" + jobDescription + '\'' +
				", isDurable='" + isDurable + '\'' +
				", triggerKey='" + triggerKey + '\'' +
				", triggerDescription='" + triggerDescription + '\'' +
				", triggerStart=" + triggerStart +
				", triggerEnd=" + triggerEnd +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ScheduleJobInfo)) return false;

		ScheduleJobInfo that = (ScheduleJobInfo) o;

		if (getJobKey() != null ? !getJobKey().equals(that.getJobKey()) : that.getJobKey() != null) return false;
		if (getJobDescription() != null ? !getJobDescription().equals(that.getJobDescription()) : that.getJobDescription() != null)
			return false;
		if (getIsDurable() != null ? !getIsDurable().equals(that.getIsDurable()) : that.getIsDurable() != null)
			return false;
		if (getTriggerKey() != null ? !getTriggerKey().equals(that.getTriggerKey()) : that.getTriggerKey() != null)
			return false;
		if (getTriggerDescription() != null ? !getTriggerDescription().equals(that.getTriggerDescription()) : that.getTriggerDescription() != null)
			return false;
		if (getTriggerStart() != null ? !getTriggerStart().equals(that.getTriggerStart()) : that.getTriggerStart() != null)
			return false;
		return getTriggerEnd() != null ? getTriggerEnd().equals(that.getTriggerEnd()) : that.getTriggerEnd() == null;
	}

	@Override
	public int hashCode() {
		int result = getJobKey() != null ? getJobKey().hashCode() : 0;
		result = 31 * result + (getJobDescription() != null ? getJobDescription().hashCode() : 0);
		result = 31 * result + (getIsDurable() != null ? getIsDurable().hashCode() : 0);
		result = 31 * result + (getTriggerKey() != null ? getTriggerKey().hashCode() : 0);
		result = 31 * result + (getTriggerDescription() != null ? getTriggerDescription().hashCode() : 0);
		result = 31 * result + (getTriggerStart() != null ? getTriggerStart().hashCode() : 0);
		result = 31 * result + (getTriggerEnd() != null ? getTriggerEnd().hashCode() : 0);
		return result;
	}
}
