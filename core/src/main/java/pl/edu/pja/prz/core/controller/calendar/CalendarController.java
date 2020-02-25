package pl.edu.pja.prz.core.controller.calendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.calendar.model.Absence;
import pl.edu.pja.prz.calendar.model.DayOffWork;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/calendar/")
//TODO: ADD @PreAuthorize annotation with proper roles from Roles.java class
public class CalendarController {
    //TODO - change method signatures are you need.
    // Below are just examples of expected method that calendar should deliver

    public List<Absence> getAllAbsences(UUID childId, LocalDate from, LocalDate to) {
        //TODO - implement this method
        return null;
    }

    public List<LocalDate> getAllDaysWhereChildWasPresent(UUID childId, LocalDate from, LocalDate to) {
        //TODO - implement this method
        return null;
    }

    public void registerAbsence(UUID childId, LocalDate date) {
        //TODO - implement this method
    }

    public void cancelAbsence(UUID childId, LocalDate date) {
        //TODO - implement this method
    }

    public List<DayOffWork> getListOfDaysOffWork(LocalDate from, LocalDate to) {
        //TODO - implement this method
        return null;
    }

    public void addDayOffWork(DayOffWork dayOffWork) {
        //TODO - implement this method
    }

    public void removeDayOffWork(LocalDate date) {
        //TODO - implement this method
    }
}
