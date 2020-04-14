package pl.edu.pja.prz.calendar.model.dto;

import java.time.LocalDate;
import java.util.UUID;

public class AbsenceRangeDto {
    private UUID childId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String reason;

    public AbsenceRangeDto() {
    }


    public AbsenceRangeDto(UUID childId, LocalDate dateFrom, LocalDate dateTo, String reason) {
    }


    public UUID getChildId() {
        return childId;
    }

    public void setChildId(UUID childId) {
        this.childId = childId;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
