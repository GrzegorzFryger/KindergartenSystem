package pl.edu.pja.prz.payments.model;

import org.quartz.Job;

public class JobInfo {
	private String name;
	private String description;
	private String classFullName;
	private Class<? extends Job> classType;

	public JobInfo(String name, String description, String classFullName, Class<? extends Job> classType) {
		this.name = name;
		this.description = description;
		this.classFullName = classFullName;
		this.classType = classType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassFullName() {
		return classFullName;
	}

	public void setClassFullName(String classFullName) {
		this.classFullName = classFullName;
	}

	public Class<? extends Job> getClassType() {
		return classType;
	}

	public void setClassType(Class classType) {
		this.classType = classType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JobInfo)) return false;

		JobInfo jobInfo = (JobInfo) o;

		if (getName() != null ? !getName().equals(jobInfo.getName()) : jobInfo.getName() != null) return false;
		if (getDescription() != null ? !getDescription().equals(jobInfo.getDescription()) : jobInfo.getDescription() != null)
			return false;
		if (getClassFullName() != null ? !getClassFullName().equals(jobInfo.getClassFullName()) : jobInfo.getClassFullName() != null)
			return false;
		return getClassType() != null ? getClassType().equals(jobInfo.getClassType()) : jobInfo.getClassType() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		result = 31 * result + (getClassFullName() != null ? getClassFullName().hashCode() : 0);
		result = 31 * result + (getClassType() != null ? getClassType().hashCode() : 0);
		return result;
	}
}
