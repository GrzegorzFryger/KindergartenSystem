package pl.edu.pja.prz.core.controller.technical;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pja.prz.mail.facade.MailFacade;
import pl.edu.pja.prz.mail.model.BaseMail;

import static pl.edu.pja.prz.commons.constants.Profiles.DEVELOPMENT;

//TODO: For now this controller is created only for testing purposes. It can be removed later on
@RestController
@RequestMapping("api/mail/")
@Profile(DEVELOPMENT)
public class MailTestController {

    private final MailFacade mailFacade;

    @Autowired
    public MailTestController(MailFacade mailFacade) {
        this.mailFacade = mailFacade;
    }

    @GetMapping("{to}/{subject}/{content}")
    public String sendTestMail(@PathVariable String to,
                                @PathVariable String subject,
                                @PathVariable String content) {
        BaseMail baseMail = new BaseMail();
        baseMail.setTo(to);
        baseMail.setSubject(subject);
        baseMail.setContent(content);
        mailFacade.sendEmail(baseMail);
        return "Request finished. Check logs.";
    }

}
