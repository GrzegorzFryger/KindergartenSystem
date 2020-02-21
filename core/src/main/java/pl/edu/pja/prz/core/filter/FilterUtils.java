package pl.edu.pja.prz.core.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import pl.edu.pja.prz.core.exception.Error;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private FilterUtils() {

    }

    public static void addErrorToResponse(String message, HttpServletResponse response) {
        logger.debug(message);
        Error error = new Error(message);
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(error);
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse error object to json", e);
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            logger.error("Failed to write response", e);
        }
    }
}
