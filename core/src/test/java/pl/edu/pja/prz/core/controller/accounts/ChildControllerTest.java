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
import pl.edu.pja.prz.account.facade.ChildFacade;
import pl.edu.pja.prz.account.model.dto.ChildDto;
import pl.edu.pja.prz.commons.model.FullName;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_ACCOUNT;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class ChildControllerTest {
  private MockMvc mvc;

  @Mock
  private ChildFacade childFacade;

  @InjectMocks
  private ChildController controller;

  @BeforeEach
  public void setUp() {
    this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  public void Should_DelegateApiCallTo_findChildById() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "child/" + UUID.randomUUID())
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    //Then
    verify(childFacade, only()).findChildById(any(UUID.class));
  }

  @Test
  public void Should_DelegateApiCallTo_searchByFullName() throws Exception {
    //Given

    //When
    mvc.perform(MockMvcRequestBuilders.get(API_ACCOUNT + "children/search/")
            .param("name", "Grzegorz")
            .param("surname", "Sykut-Jeżyna")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

    //Then
    verify(childFacade, only()).searchByFullName(any(FullName.class));
  }

  @Test
  public void Should_DelegateApiCallTo_updateChild() throws Exception {
    //Given
    String json = convertToJson(new ChildDto());

    //When
    mvc.perform(MockMvcRequestBuilders.put(API_ACCOUNT + "child")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(childFacade, only()).updateChild(any(ChildDto.class));
  }

  @Test
  public void Should_DelegateApiCallTo_createChild() throws Exception {
    //Given
    String json = convertToJson(new ChildDto());

    //When
    mvc.perform(MockMvcRequestBuilders.post(API_ACCOUNT + "child")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(json))
        .andExpect(status().isOk());

    //Then
    verify(childFacade, only()).createChild(any(ChildDto.class));
  }

}
