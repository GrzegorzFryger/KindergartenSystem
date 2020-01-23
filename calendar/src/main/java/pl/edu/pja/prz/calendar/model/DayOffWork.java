package pl.edu.pja.prz.calendar.model;

import pl.edu.pja.prz.calendar.model.enums.EventType;
import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;
import java.time.LocalDate;

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
}
