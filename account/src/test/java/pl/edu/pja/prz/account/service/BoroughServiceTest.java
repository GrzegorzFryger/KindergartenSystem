package pl.edu.pja.prz.account.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.ChildStatus;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.repository.BoroughRepository;
import pl.edu.pja.prz.account.utilites.ChildBuilder;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoroughServiceTest {
    private Address address;
    private Phone phone;
    private FullName fullName;

    @Mock
    private BoroughRepository boroughRepository;
    @Mock
    private ChildService childService;
    @Mock
    private BoroughService boroughService;

    @BeforeEach
    void setUp() {
        address = new Address("70-700", "City", "Street 256");
        phone = new Phone("123132123");
        fullName = new FullName("TestName", "TestSurname");
        boroughService = new BoroughService(boroughRepository, childService);
    }

    @Test
    void should_creatBorough() {
        //given
        var borough = new Borough("Test borough",
                new Address("70-700", "City", "Street 256"),
                new Phone("123132123"),
                "test@test.com",
                "99576122623"
        );

        //when
        when(boroughRepository.save(any(Borough.class))).thenReturn(borough);
        var returnedBorough = boroughService.createBorough(borough);

        //then
        assertNotNull(returnedBorough);
        verify(boroughRepository, times(1)).save(borough);
    }

    @Test
    void should_addChildToBorough() {
        //given
        var childId = UUID.randomUUID();
        var boroughId = 1L;

        var borough = new Borough("Test borough",
                new Address("70-700", "City", "Street 256"),
                new Phone("123132123"),
                "test@test.com",
                "99576122623"
        );

        var child = ChildBuilder.aChild()
                .withAge(new Age(LocalDate.of(1994, 7, 11)))
                .withPeselNumber("NOT_SET")
                .withFullName(fullName)
                .withAddress(address)
                .withGender(Gender.MALE)
                .withChildStatuses(Set.of(ChildStatus.NEW))
                .build();

        Borough boroughSpy = Mockito.spy(borough);

        //when
        when(boroughRepository.findById(eq(1L))).thenReturn(Optional.of(boroughSpy));
        when(childService.getById(eq(childId))).thenReturn(child);
        when(boroughRepository.save(eq(borough))).thenReturn(boroughSpy);

        boroughService.addChildToBorough(boroughId, childId);

        //then
        verify(boroughSpy, times(1)).addChild(any(Child.class));
        verify(boroughRepository, times(1)).save(any(Borough.class));
    }

    @Test
    void should_findBorough() {
        //given
        var id = Long.valueOf(1);

        var borough = new Borough("Test borough",
                new Address("70-700", "City", "Street 256"),
                new Phone("123132123"),
                "test@test.com",
                "99576122623"
        );

        //when
        when(boroughRepository.findById(1L)).thenReturn(Optional.of(borough));
        var returnedValue = boroughService.findBorough(1L);

        //then
        verify(boroughRepository, times(1)).findById(1L);
    }

    @Test
    void should_updateBorough() {
        //given
        var borough = new Borough("Test borough",
                new Address("70-700", "City", "Street 256"),
                new Phone("123132123"),
                "test@test.com",
                "99576122623"
        );
        borough.setId(1L);

        //when
        when(boroughRepository.findById(1L)).thenReturn(Optional.of(borough));
        var updatedBorough = boroughService.updateBorough(borough);

        //then
        verify(boroughRepository, times(1)).save(borough);
    }

    @Test
    public void Should_DeleteBorough() {
        //Given
        var borough = new Borough("Test borough",
                new Address("70-700", "City", "Street 256"),
                new Phone("123132123"),
                "test@test.com",
                "99576122623"
        );

        //When
        when(boroughRepository.findById(anyLong())).thenReturn(Optional.of(borough));
        boroughService.deleteBorough(1L);

        //Then
        verify(boroughRepository, times(1)).delete(any(Borough.class));
    }

    @Test
    public void Should_ThrowException_When_BoroughToDeleteDoesNotExists() {
        //Given

        //When
        when(boroughRepository.findById(anyLong())).thenReturn(Optional.empty());
        Assertions.assertThrows(ElementNotFoundException.class, () -> {
            boroughService.deleteBorough(1L);
        });

        //Then
    }
}
