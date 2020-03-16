package pl.edu.pja.prz.core.controller.accounts;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

import java.util.UUID;
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

@ExtendWith(MockitoExtension.class)
class GuardianControllerTest {
  private MockMvc mvc;

  @Mock
  private GuardianFacade guardianFacade;

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

    //When
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "guardian")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(guardianFacade, only()).createGuardian(any(AccountDto.class));
  }


}
