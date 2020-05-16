package pl.edu.pja.prz.commons.event;

import org.junit.jupiter.api.Test;
import pl.edu.pja.prz.commons.model.GuardianChildDependency;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AppendChildEventTest {

    @Test
    public void Should_CreateAppendChildEvent() {
        //Given
        Object source = new Object();
        GuardianChildDependency dependency = new GuardianChildDependency();
        dependency.setChildId(UUID.randomUUID());
        dependency.setGuardianId(UUID.randomUUID());

        //When
        AppendChildEvent appendChildEvent = new AppendChildEvent(source, dependency);

        //Then
        assertNotNull(appendChildEvent);
        assertNotNull(appendChildEvent.getGuardianChildDependency());
    }

}