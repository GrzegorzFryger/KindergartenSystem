package pl.edu.pja.prz.account.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.facade.dto.AccountDto;
import pl.edu.pja.prz.account.facade.dto.GuardianDto;
import pl.edu.pja.prz.account.facade.mapper.GuardianMapper;
import pl.edu.pja.prz.account.model.Guardian;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.enums.AccountStatus;
import pl.edu.pja.prz.commons.model.Address;
import pl.edu.pja.prz.commons.model.FullName;
import pl.edu.pja.prz.commons.model.Phone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class GuardianMapperTest {
    @Mock
    private GuardianMapper guardianMapper;

    private AccountDto accountDto;
    private Guardian guardian;
    private GuardianDto guardianDto;
    private Person person;

    private FullName fullName;
    private Address address;
    private Phone phone;
    private String email;
    private AccountStatus status;

    @BeforeEach
    public void setUp() {

        this.guardianMapper = Mappers.getMapper(GuardianMapper.class);
        accountDto = new AccountDto();
        guardian = new Guardian();
        guardianDto = new GuardianDto();
        person = new Person();

        fullName = new FullName("TestName", "TestSurname");
        address = new Address("70-700", "City", "Street 256");
        phone = new Phone("123132123");
        email = "test@test.com";
        status = AccountStatus.ACTIVE;

        accountDto.setName(fullName.getName());
        accountDto.setSurname(fullName.getSurname());
        accountDto.setPostalCode(address.getPostalCode());
        accountDto.setCity(address.getCity());
        accountDto.setStreetNumber(address.getStreetNumber());
        accountDto.setPhone(phone.getPhone());
        accountDto.setEmail(email);
        accountDto.setStatus(status);

        person.setFullName(fullName);
        person.setAddress(address);
        person.setPhoneNumber(phone);

        guardian.setFullName(fullName);
        guardian.setAddress(address);
        guardian.setPhoneNumber(phone);
        guardian.setEmail(email);
        guardian.setAccountStatus(status);

        guardianDto.setName(fullName.getName());
        guardianDto.setSurname(fullName.getSurname());
        guardianDto.setPostalCode(address.getPostalCode());
        guardianDto.setCity(address.getCity());
        guardianDto.setStreetNumber(address.getStreetNumber());
        guardianDto.setPhone(phone.getPhone());
        guardianDto.setStatus(status);
        guardianDto.setEmail(email);
    }

    @Test
    public void Should_MapFromGuardian() {

        //When
        GuardianDto newGuardianDto = guardianMapper.fromGuardian(guardian);

        //Then
        verifyDto(newGuardianDto);

    }

    @Test
    public void Should_MapToGuardian() {

        //When
        Guardian newGuardian = guardianMapper.toGuardian(guardianDto);

        //Then
        verifyGuardian(newGuardian);
    }

    @Test
    public void Should_MapToPerson() {

        //When
        Person newPerson = guardianMapper.toPerson(accountDto);

        //Then
        verifyPerson(newPerson);

    }

    private void verifyDto(GuardianDto guardianDto) {
        assertNotNull(guardianDto);
        assertEquals("TestName", guardianDto.getName());
        assertEquals("TestSurname", guardianDto.getSurname());
        assertEquals("70-700", guardianDto.getPostalCode());
        assertEquals("City", guardianDto.getCity());
        assertEquals("Street 256", guardianDto.getStreetNumber());
        assertEquals("123132123", guardianDto.getPhone());
        assertEquals("test@test.com", guardianDto.getEmail());
        assertEquals(AccountStatus.ACTIVE, guardianDto.getStatus());
    }

    private void verifyGuardian(Guardian guardian) {
        assertNotNull(guardian);
        assertEquals(fullName, guardian.getFullName());
        assertEquals(address, guardian.getAddress());
        assertEquals(phone, guardian.getPhoneNumber());
        assertEquals("test@test.com", guardian.getEmail());
        assertEquals(AccountStatus.ACTIVE, guardian.getAccountStatus());
    }

    private void verifyPerson(Person person) {
        assertNotNull(person);
        assertEquals(fullName, person.getFullName());
        assertEquals(address, person.getAddress());
        assertEquals(phone, person.getPhoneNumber());
    }
}
