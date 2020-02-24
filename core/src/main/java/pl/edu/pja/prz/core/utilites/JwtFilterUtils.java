package pl.edu.pja.prz.core.utilites;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.pja.prz.core.exception.Error;
import pl.edu.pja.prz.core.model.AuthDto;
import pl.edu.pja.prz.core.security.JwtAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

public class JwtFilterUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtFilterUtils.class);

    private JwtFilterUtils() {

    }

    public static void addErrorToResponse(String message, HttpServletResponse response) {
        logger.debug(message);

        response.setStatus(METHOD_NOT_ALLOWED.value());

        try {
            new ObjectMapper()
                    .writeValue(response.getWriter(), new Error(message));
        } catch (IOException e) {
            logger.error("Failed to retrieve response writer", e);
        }
    }

    public static AuthDto getAuthDto(HttpServletRequest request) {
        ObjectMapper mapper = new ObjectMapper();
        AuthDto dto = null;
        try {
            dto = mapper.readValue(request.getInputStream(), AuthDto.class);
        } catch (IOException e) {
            logger.error("Failed to parse DTO with username and password", e);
        }
        return dto;
    }
}
