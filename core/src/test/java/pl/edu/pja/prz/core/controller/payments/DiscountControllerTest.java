package pl.edu.pja.prz.core.controller.payments;

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
import pl.edu.pja.prz.payments.facade.DiscountFacade;
import pl.edu.pja.prz.payments.model.dto.DiscountDto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_PAYMENTS;
import static pl.edu.pja.prz.core.util.JsonUtil.convertToJson;

@ExtendWith(MockitoExtension.class)
class DiscountControllerTest {

    private MockMvc mvc;

    @Mock
    private DiscountFacade facade;

    @InjectMocks
    private DiscountController controller;

    @BeforeEach
    public void setUp() {
        this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void Should_DelegateApiCallTo_getByIdMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "discount/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).getById(anyLong());
    }

    @Test
    public void Should_DelegateApiCallTo_getAllDiscountsMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.get(API_PAYMENTS + "discounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).getAllDiscounts();
    }

    @Test
    public void Should_DelegateApiCallTo_createDiscountMethod() throws Exception {
        //Given
        String json = convertToJson(new DiscountDto());

        //When
        mvc.perform(MockMvcRequestBuilders.post(API_PAYMENTS + "discount")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).createDiscount(any(DiscountDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_updateDiscountMethod() throws Exception {
        //Given
        String json = convertToJson(new DiscountDto());

        //When
        mvc.perform(MockMvcRequestBuilders.put(API_PAYMENTS + "discount")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).updateDiscount(any(DiscountDto.class));
    }

    @Test
    public void Should_DelegateApiCallTo_deleteDiscountMethod() throws Exception {
        //Given

        //When
        mvc.perform(MockMvcRequestBuilders.delete(API_PAYMENTS + "discount/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //Then
        verify(facade, only()).deleteDiscount(anyLong());
    }

}