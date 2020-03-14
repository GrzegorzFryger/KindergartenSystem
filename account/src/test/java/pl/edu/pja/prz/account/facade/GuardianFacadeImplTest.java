package pl.edu.pja.prz.account.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.pja.prz.account.mapper.ChildMapper;
import pl.edu.pja.prz.account.mapper.ChildMapperImpl;
import pl.edu.pja.prz.account.mapper.GuardianMapper;
import pl.edu.pja.prz.account.mapper.GuardianMapperImpl;
import pl.edu.pja.prz.account.model.Person;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.service.GuardianService;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GuardianFacadeImplTest {
    private GuardianMapper guardianMapper;
    @Mock
    private GuardianService guardianService;
    private ChildMapper childMapper;
    private GuardianFacade guardianFacade;

    private AccountDto accountDto;

    @BeforeEach
    public void setUp() {
        guardianMapper = new GuardianMapperImpl();
        childMapper = new ChildMapperImpl();

        guardianFacade = new GuardianFacadeImpl(guardianMapper, guardianService, childMapper);

        accountDto = new AccountDto();
        accountDto.setName("Name");
        accountDto.setSurname("Surname");
        accountDto.setCity("City");
        accountDto.setPostalCode("12345");
        accountDto.setStreetNumber("Street Number");
        accountDto.setEmail("Email");
        accountDto.setPhone("123 123 133");
    }

    @Test
    public void Should_CreateGuardian() {
        //Given

        //When
        guardianFacade.createGuardian(accountDto);

        //Then
        verify(guardianService, only()).createGuardianAccount(any(Person.class), anyString());
    }

    @Test
    public void Should_FindGuardianById() {
        //Given

        //When
        guardianFacade.findGuardianById(UUID.randomUUID());

        //Then
        verify(guardianService, only()).getById(any(UUID.class));
    }

    @Test
    public void Should_FindAllGuardians() {
        //Given

        //When
        guardianFacade.findAllGuardians();

        //Then
        verify(guardianService, only()).getAll();
    }

    @Test
    public void Should_FindAllGuardianChildren() {
        //Given

        //When
        guardianFacade.findAllGuardianChildren(UUID.randomUUID());

        //Then
        verify(guardianService, only()).getAllChildren(any(UUID.class));
    }

    @Test
    public void Should_FindByFullNameOrAddress() throws Exception {
        //Given

        //When
        guardianFacade.findByFullNameOrAddress("Name", "Surname", "Street");

        //Then
        verify(guardianService, only()).findByFullNameOrAddressReadOnly(any(FullName.class), anyString());
    }
}
