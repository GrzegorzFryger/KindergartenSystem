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
import pl.edu.pja.prz.account.facade.EmployeeFacade;
import pl.edu.pja.prz.account.model.dto.AccountDto;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
  private MockMvc mvc;

  @Mock
  private EmployeeFacade employeeFacade;

  @InjectMocks
  private EmployeeController controller;

  @BeforeEach
  public void setUp() {
    this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void Should_DelegateApiCallTo_findEmployeeById() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "employee/" + UUID.randomUUID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(employeeFacade, only()).findById(any(UUID.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createEmployee() throws Exception {
    //Given
    String json = convertToJson(new AccountDto());

    //When
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "employee")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(employeeFacade, only()).createEmployee(any(AccountDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createAdministratorAccount() throws Exception {
    //Given
    String json = convertToJson(new AccountDto());

    //When
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "employeeAdmin")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(employeeFacade, only()).createAdministratorAccount(any(AccountDto.class));
  }

}
