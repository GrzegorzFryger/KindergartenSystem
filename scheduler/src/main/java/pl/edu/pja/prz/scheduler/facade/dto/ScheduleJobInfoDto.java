package pl.edu.pja.prz.scheduler.facade.dto;

import java.util.Date;

public class ScheduleJobInfoDto {
	private String jobKey;
	private String jobDescription;
	private String isDurable;
	private String triggerKey;
	private String triggerDescription;
	private Date triggerStart;
	private Date triggerEnd;

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
}
