package pl.edu.pja.prz.calendar.model;

import org.hibernate.annotations.Type;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Absence extends BaseEntityLong {
	@Type(type = "uuid-char")
	@Column(length = 36)
	@NotNull
	private UUID childId;
	@NotNull
	private LocalDate date;
	private String reason;

	public UUID getChildId() {
		return childId;
	}

	public void setChildId(UUID childId) {
		this.childId = childId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "Absence{" +
				"childId=" + childId +
				", date=" + date +
				", reason='" + reason + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		Absence absence = (Absence) o;
		return childId.equals(absence.childId) &&
				date.equals(absence.date) &&
				Objects.equals(reason, absence.reason);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), childId, date, reason);
	}
}
