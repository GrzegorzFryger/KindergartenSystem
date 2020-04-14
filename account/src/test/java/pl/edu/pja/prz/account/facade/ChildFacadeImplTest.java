package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.exception.MoreThanOneElement;
import pl.edu.pja.prz.account.mapper.ChildMapper;
import pl.edu.pja.prz.account.mapper.ChildMapperImpl;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.Age;
import pl.edu.pja.prz.account.model.value.StudyPeriod;
import pl.edu.pja.prz.account.service.ChildService;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChildFacadeImplTest {

    private ChildMapper childMapper;
    @Mock
    private ChildService childService;
    private ChildFacade childFacade;

    private ChildDto childDto;

    @BeforeEach
    public void setUp() {
        childMapper = new ChildMapperImpl();

        childFacade = new ChildFacadeImpl(childMapper, childService);

        childDto = new ChildDto();
        childDto.setId(UUID.randomUUID());
        childDto.setName("Name");
        childDto.setSurname("Surname");
        childDto.setCity("City");
        childDto.setStreetNumber("Street");
        childDto.setGender(Gender.MALE);
        childDto.setDateOfBirth(LocalDate.now());
        childDto.setStartDate(LocalDate.now());
        childDto.setEndDate(LocalDate.now());
    }

    @Test
    public void Should_CreateChild_WhenPeselIsNull() {
        //Given

        //When
        childFacade.createChild(childDto);

        //Then
        verify(childService, only()).createChild(any(Address.class), any(Age.class),
                any(FullName.class), any(Gender.class), any(StudyPeriod.class));
    }

    @Test
    public void Should_CreateChild_WhenPeselIsProvided() {
        //Given
        childDto.setPesel("Some PESEL number");

        //When
        childFacade.createChild(childDto);

        //Then
        verify(childService, only()).createChild(any(Address.class), any(FullName.class),
                anyString(), any(StudyPeriod.class));
    }


    @Test
    public void Should_UpdateChild() {
        //Given

        //When
        childFacade.updateChild(childDto);

        //Then
        verify(childService, only()).update(any(Child.class));
    }

    @Test
    public void Should_FindChildById() {
        //Given

        //When
        childFacade.findChildById(UUID.randomUUID());

        //Then
        verify(childService, only()).getById(any(UUID.class));
    }

    @Test
    public void Should_FindByFullNameOrAddress() throws MoreThanOneElement {
        //Given

        //When
        childFacade.findByFullNameOrAddress("Name", "Surname", "Street");

        //Then
        verify(childService, only()).findByFullNameOrAddressReadOnly(any(FullName.class), anyString());
    }

    @Test
    public void Should_SearchByFullName() {
        //Given

        //When
        childFacade.searchByFullName(new FullName("Grzegorz", "Brzęczyszczykiewicz"));

        //Then
        verify(childService, only()).findByFullNameReadOnly(any(FullName.class));
    }
}
