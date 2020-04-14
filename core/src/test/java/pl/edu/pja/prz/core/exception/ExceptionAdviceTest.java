package pl.edu.pja.prz.core.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.StatusResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.edu.pja.prz.commons.exception.BusinessException;
import pl.edu.pja.prz.commons.exception.ElementNotFoundException;
import pl.edu.pja.prz.commons.exception.EmptyInputException;
import pl.edu.pja.prz.commons.exception.IncorrectInputTypeException;
import pl.edu.pja.prz.core.controller.receivables.TransactionsController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_RECEIVABLES;

@ExtendWith(MockitoExtension.class)
class ExceptionAdviceTest {

    private MockMvc mockMvc;

    @Mock
    private TransactionsController testController;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ExceptionAdvice(), testController).build();
    }

    @Test
    public void Should_ReturnBadRequestCode_When_BusinessExceptionIsThrown() throws Exception {
        testException(new BusinessException("Unexpected Exception"), status().isBadRequest());
    }

    @Test
    public void Should_ReturnBadRequestCode_When_IncorrectInputTypeExceptionIsThrown() throws Exception {
        testException(new IncorrectInputTypeException("Unexpected Exception"), status().isBadRequest());
    }

    @Test
    public void Should_ReturnBadRequestCode_When_AccessDeniedExceptionIsThrown() throws Exception {
        testException(new AccessDeniedException("Unexpected Exception"), status().isBadRequest());
    }

    @Test
    public void Should_ReturnNotFoundCode_When_ElementNotFoundExceptionIsThrown() throws Exception {
        testException(new ElementNotFoundException("Unexpected Exception"), status().isNotFound());
    }

    @Test
    public void Should_ReturnNotFoundCode_When_EmptyInputExceptionIsThrown() throws Exception {
        testException(new EmptyInputException("Unexpected Exception"), status().isNotFound());
    }

    @Test
    public void Should_ReturnInternalServerErrorCode_When_AnyOtherExceptionIsThrown() throws Exception {
        testException(new RuntimeException("Unexpected Exception"), status().isInternalServerError());
    }

    private void testException(Exception exception, ResultMatcher expectedResult) throws Exception {
        //Given

        //When
        when(testController.getAllTransactions()).thenThrow(exception);

        //Then
        mockMvc.perform(MockMvcRequestBuilders.get(API_RECEIVABLES + "transactions"))
                .andExpect(expectedResult);
    }
}
