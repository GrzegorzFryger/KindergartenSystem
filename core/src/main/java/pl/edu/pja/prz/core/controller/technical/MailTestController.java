package pl.edu.pja.prz.core.controller.technical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.mail.facade.MailFacade;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.model.test.BaseMailTestDto;

import static pl.edu.pja.prz.commons.constants.Profiles.DEVELOPMENT;
import static pl.edu.pja.prz.commons.constants.Roles.HAS_ROLE_ADMIN;
import static pl.edu.pja.prz.core.controller.RequestMappings.API_MAIL;

@RestController
@RequestMapping(API_MAIL)
@Profile(DEVELOPMENT)
@PreAuthorize(HAS_ROLE_ADMIN)
public class MailTestController {

    private final MailFacade mailFacade;

    @Autowired
    public MailTestController(MailFacade mailFacade) {
        this.mailFacade = mailFacade;
    }

    @PostMapping("send")
    public String sendTestMail(@RequestBody BaseMailTestDto dto) {
        BaseMail baseMail = new BaseMail();
        baseMail.setTo(dto.getTo());
        baseMail.setSubject(dto.getSubject());
        baseMail.setContent(dto.getContent());
        mailFacade.sendEmail(baseMail);
        return "Request finished. Check logs.";
    }

}
