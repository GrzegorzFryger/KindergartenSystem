package pl.edu.pja.prz.payments.model;

import javax.persistence.Entity;

@Entity
public class JobSpecification extends BaseEntity<Long> {
	private String name;
	private String group;
	private String description;
	private String classOfJob;

	public JobSpecification() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getClassOfJob() {
		return classOfJob;
	}

	public void setClassOfJob(String classOfJob) {
		this.classOfJob = classOfJob;
	}
}
