package pl.edu.pja.prz.scheduler.facade.dto;

public class JobInfoDto {
	private String name;
	private String description;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof JobInfoDto)) return false;

		JobInfoDto that = (JobInfoDto) o;

		if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
		return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;
	}

	@Override
	public int hashCode() {
		int result = getName() != null ? getName().hashCode() : 0;
		result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
		return result;
	}
}
