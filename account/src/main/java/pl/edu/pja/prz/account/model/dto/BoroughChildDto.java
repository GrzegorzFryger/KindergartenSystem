package pl.edu.pja.prz.account.model.dto;

import java.util.UUID;

public class BoroughChildDto {
	private Long boroughId;
	private UUID childId;

	public BoroughChildDto() {
	}

	public Long getBoroughId() {
		return boroughId;
	}

	public void setBoroughId(Long boroughId) {
		this.boroughId = boroughId;
	}

	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}
}
