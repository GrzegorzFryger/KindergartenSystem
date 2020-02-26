package pl.edu.pja.prz.calendar.model;

import pl.edu.pja.prz.calendar.model.enums.EventType;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class DayOffWork extends BaseEntityLong {
	private LocalDate date;
	private String name;
	private EventType eventType;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	@Override
	public String toString() {
		return "DayOffWork{" +
				"date=" + date +
				", name='" + name + '\'' +
				", eventType=" + eventType +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		DayOffWork that = (DayOffWork) o;
		return Objects.equals(date, that.date) &&
				Objects.equals(name, that.name) &&
				eventType == that.eventType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), date, name, eventType);
	}
}
