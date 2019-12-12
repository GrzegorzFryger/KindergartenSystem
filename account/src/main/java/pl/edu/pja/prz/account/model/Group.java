package pl.edu.pja.prz.account.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CHILDREN_GROUP")
public class Group {
	@Id
	private Long groupId;
	private String additionalInfo;

	Group() {
	}

	public Group(Long groupId, String additionalInfo) {
		this.groupId = groupId;
		this.additionalInfo = additionalInfo;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
}
