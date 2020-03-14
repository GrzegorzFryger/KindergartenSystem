package pl.edu.pja.prz.core.controller.accounts;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

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
import pl.edu.pja.prz.account.facade.BoroughFacade;
import pl.edu.pja.prz.account.model.dto.BoroughChildDto;
import pl.edu.pja.prz.account.model.dto.BoroughDto;

@ExtendWith(MockitoExtension.class)
class BoroughControllerTest {
  private MockMvc mvc;

  @Mock
  private BoroughFacade boroughFacade;

  @InjectMocks
  private BoroughController controller;

  @BeforeEach
  public void setUp() {
    this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void Should_DelegateApiCallTo_findBorough() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "borough/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).findBorough(anyLong());
  }

  @Test
  public void Should_DelegateApiCallTo_createBorough() throws Exception {
    //Given
    String json = convertToJson(new BoroughDto());

    //When
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "borough")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).createBorough(any(BoroughDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_updateBorough() throws Exception {
    //Given
    String json = convertToJson(new BoroughDto());

    //When
    mvc.perform(MockMvcRequestBuilders.put(API_ACCOUNT + "borough")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).updateBorough(any(BoroughDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_findAllChildrenFrom() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "borough/1/children")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).findAllChildrenFrom(anyLong());
  }

  @Test
  public void Should_DelegateApiCallTo_appendChild() throws Exception {
    //Given
    String json = convertToJson(new BoroughChildDto());

    //When
    mvc.perform(MockMvcRequestBuilders.put(API_ACCOUNT + "borough/child")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).appendChild(any(BoroughChildDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_deleteBorough() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.delete(API_ACCOUNT + "borough/1")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(boroughFacade, only()).deleteBorough(anyLong());
  }

}
