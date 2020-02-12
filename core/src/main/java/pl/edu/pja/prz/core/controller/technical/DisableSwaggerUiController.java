package pl.edu.pja.prz.core.controller.technical;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static pl.edu.pja.prz.commons.constants.Profiles.PRODUCTION;

@Profile(PRODUCTION)
@RestController
public class DisableSwaggerUiController {

    @GetMapping("swagger-ui.html")
    public void getSwagger(HttpServletResponse httpResponse) {
        httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
