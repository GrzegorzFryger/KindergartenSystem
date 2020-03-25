package pl.edu.pja.prz.core.controller.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pja.prz.account.facade.GuardianFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.account.model.dto.GuardianChildAssociationDto;
import pl.edu.pja.prz.account.model.dto.GuardianDto;
import pl.edu.pja.prz.receivables.facade.ReceivablesFacade;

import java.util.HashSet;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class GuardianControllerTest {
  private MockMvc mvc;

  @Mock
  private GuardianFacade guardianFacade;

  @Mock
  private ReceivablesFacade receivablesFacade;

  @InjectMocks
  private GuardianController controller;

  @BeforeEach
  public void setUp() {
    this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void Should_DelegateApiCallTo_findGuardianById() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "guardian/" + UUID.randomUUID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).findGuardianById(any(UUID.class));
  }

  @Test
  public void Should_DelegateApiCallTo_findAllGuardians() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "guardians")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).findAllGuardians();
  }

  @Test
  public void Should_DelegateApiCallTo_findAllGuardianChildren() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "guardians/" + UUID.randomUUID() + "/children")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).findAllGuardianChildren(any(UUID.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createGuardian() throws Exception {
    //Given
    String json = convertToJson(new AccountDto());
    GuardianDto guardianDto = new GuardianDto();

    //When
    when(guardianFacade.createGuardian(any(AccountDto.class))).thenReturn(guardianDto);
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "guardian")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).createGuardian(any(AccountDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createGuardian_And_AppendChild() throws Exception {
    //Given
    UUID childId = UUID.randomUUID();
    UUID guardianId = UUID.randomUUID();
    GuardianChildAssociationDto dto = new GuardianChildAssociationDto();
    dto.setChildId(childId);
    dto.setGuardianId(guardianId);

    String json = convertToJson(dto);

    GuardianDto guardianDto = new GuardianDto();
    guardianDto.setChildren(new HashSet<>());
    ChildDto childDto = new ChildDto();
    childDto.setId(childId);
    guardianDto.getChildren().add(childDto);

    //When
    when(guardianFacade.appendGuardianToChild(any(GuardianChildAssociationDto.class))).thenReturn(guardianDto);
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "guardian/append-child")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).appendGuardianToChild(any(GuardianChildAssociationDto.class));
    verify(receivablesFacade, only()).addTransactionMapping(any(UUID.class), any(UUID.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createGuardian_And_NotAppendChildIfChildIdIsDifferent() throws Exception {
    //Given
    UUID childId = UUID.randomUUID();
    UUID guardianId = UUID.randomUUID();
    GuardianChildAssociationDto dto = new GuardianChildAssociationDto();
    dto.setChildId(childId);
    dto.setGuardianId(guardianId);

    String json = convertToJson(dto);

    GuardianDto guardianDto = new GuardianDto();
    guardianDto.setChildren(new HashSet<>());
    ChildDto childDto = new ChildDto();
    childDto.setId(UUID.randomUUID()); //Some new id - different from what is in childAssociationDto
    guardianDto.getChildren().add(childDto);

    //When
    when(guardianFacade.appendGuardianToChild(any(GuardianChildAssociationDto.class))).thenReturn(guardianDto);
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "guardian/append-child")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON).content(json))
            .andExpect(status().isNotFound());

    //Then
    verify(guardianFacade, only()).appendGuardianToChild(any(GuardianChildAssociationDto.class));
  }


}
