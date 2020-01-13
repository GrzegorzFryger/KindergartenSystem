package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.facade.dto.ChildDto;
import pl.edu.pja.prz.account.facade.mapper.ChildMapper;
import pl.edu.pja.prz.account.model.Borough;
import pl.edu.pja.prz.account.model.Child;
import pl.edu.pja.prz.account.model.enums.Gender;
import pl.edu.pja.prz.account.model.value.*;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ChildMapperTest {
    @Mock
    private ChildMapper childMapper;

    private Child child;
    private ChildDto childDto;

    private FullName fullName;
    private Address address;
    private StudyPeriod studyPeriod;
    private Age ageGiven;
    private String pesel;
    private Gender gender;
    private Borough borough;

    @BeforeEach
    public void setUp() {
        child = new Child();
        childDto = new ChildDto();
        borough = new Borough();


        fullName = new FullName("TestName", "TestSurname");
        address = new Address("70-700", "City", "Street 256");
        studyPeriod = new StudyPeriod(LocalDate.of(2014, Month.SEPTEMBER, 01),
                LocalDate.of(2015, Month.JULY, 26));
        ageGiven = new Age(LocalDate.of(2008, Month.FEBRUARY, 15));
        pesel = "00440758725";
        gender = Gender.MALE;
        borough.setId(1L);

        child.setFullName(fullName);
        child.setAddress(address);
        child.setAge(ageGiven);
        child.setBorough(borough);
        child.setGender(gender);
        child.setPeselNumber(pesel);
        child.setStudyPeriod(studyPeriod);

        childDto.setName(fullName.getName());
        childDto.setSurname(fullName.getSurname());
        childDto.setPostalCode(address.getPostalCode());
        childDto.setCity(address.getCity());
        childDto.setStreetNumber(address.getStreetNumber());
        childDto.setPesel(pesel);
        childDto.setGender(gender);
        childDto.setStartDate(studyPeriod.getAdditionDate());
        childDto.setEndDate(studyPeriod.getEndingDate());
        childDto.setDateOfBirth(ageGiven.getDateOfBirth());
        childDto.setBoroughId(borough.getId());
    }

    @Test
    public void Should_MapFromChild() {
        //Given
        when(childMapper.fromChild(any(Child.class))).thenReturn(childDto);

        //When
        ChildDto newChildDto = childMapper.fromChild(child);

        //Then
        verifyFromChild(newChildDto);
        verify(childMapper, times(1)).fromChild(any(Child.class));

    }

    @Test
    public void Should_MapToAge() {
        //Given
        when(childMapper.toAge(any(ChildDto.class))).thenReturn(ageGiven);

        //When
        Age newAge = childMapper.toAge(childDto);
        //Then
        verifyToAge(newAge);
        verify(childMapper, times(1)).toAge(any(ChildDto.class));

    }

    @Test
    public void Should_MapToAddress() {
        //Given
        when(childMapper.toAddress(any(ChildDto.class))).thenReturn(address);

        //When
        Address newAddress = childMapper.toAddress(childDto);

        //Then
        verifyAddress(newAddress);
        verify(childMapper, times(1)).toAddress(any(ChildDto.class));
    }

    @Test
    public void Should_MapToStudyPeriod() {
        //Given
        when(childMapper.toStudyPeriod(any(ChildDto.class))).thenReturn(studyPeriod);

        //When
        StudyPeriod newStudyPeriod = childMapper.toStudyPeriod(childDto);

        //Then
        verifyStudyPeriod(newStudyPeriod);
        verify(childMapper, times(1)).toStudyPeriod(any(ChildDto.class));
    }

    @Test
    public void Should_MapToFullName() {
        //Given
        when(childMapper.toFullName(any(ChildDto.class))).thenReturn(fullName);

        //When
        FullName newFullName = childMapper.toFullName(childDto);

        //Then
        verifyFullName(newFullName);
        verify(childMapper, times(1)).toFullName(any(ChildDto.class));
    }

    private void verifyFromChild(ChildDto childDto) {
        assertNotNull(childDto);
        assertEquals(fullName.getName(), childDto.getName());
        assertEquals(fullName.getSurname(), childDto.getSurname());
        assertEquals(address.getPostalCode(), childDto.getPostalCode());
        assertEquals(address.getCity(), childDto.getCity());
        assertEquals(address.getStreetNumber(), childDto.getStreetNumber());
        assertEquals(pesel, childDto.getPesel());
        assertEquals(gender, childDto.getGender());
        assertEquals(studyPeriod.getAdditionDate(), childDto.getStartDate());
        assertEquals(studyPeriod.getEndingDate(), childDto.getEndDate());
        assertEquals(ageGiven.getDateOfBirth(), childDto.getDateOfBirth());
        assertEquals(borough.getId(), childDto.getBoroughId());
    }

    private void verifyToAge(Age age) {
        assertNotNull(age);
        assertEquals(ageGiven, age);
    }

    private void verifyAddress(Address address) {
        assertNotNull(address);
        assertEquals("70-700", address.getPostalCode());
        assertEquals("City", address.getCity());
        assertEquals("Street 256", address.getStreetNumber());
    }

    private void verifyStudyPeriod(StudyPeriod studyPeriod) {
        assertNotNull(studyPeriod);
        assertEquals(LocalDate.of(2014, Month.SEPTEMBER, 01), studyPeriod.getAdditionDate());
        assertEquals(LocalDate.of(2015, Month.JULY, 26), studyPeriod.getEndingDate());
    }

    private void verifyFullName(FullName fullName) {
        assertNotNull(fullName);
        assertEquals("TestName", fullName.getName());
        assertEquals("TestSurname", fullName.getSurname());
    }
}
