package pl.edu.pja.prz.calendar.model;

import pl.edu.pja.prz.commons.model.BaseEntityLong;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Absence extends BaseEntityLong {
    private UUID childId;
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
}
