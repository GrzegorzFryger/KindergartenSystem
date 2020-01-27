package pl.edu.pja.prz.core.controller.technical;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.commons.constants.Profiles;

import javax.servlet.http.HttpServletResponse;

@Profile(Profiles.PRODUCTION)
@RestController
public class DisableSwaggerUiController {

    @RequestMapping(value = "swagger-ui.html", method = RequestMethod.GET)
    public void getSwagger(HttpServletResponse httpResponse) {
        httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
    }
}
