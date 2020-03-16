package pl.edu.pja.prz.calendar.model.dto;

import pl.edu.pja.prz.calendar.model.enums.EventType;

import java.time.LocalDate;

public class DayOffWorkDto {
	private Long id;
	private LocalDate date;
	private String name;
	private EventType eventType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
}
