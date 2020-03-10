package pl.edu.pja.prz.mail.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.service.MailService;

@Service
public class MailFacade {
    private final MailService mailService;

    @Autowired
    public MailFacade(MailService mailService) {
        this.mailService = mailService;
    }

    public void sendEmail(BaseMail baseMail) {
        mailService.sendEmail(baseMail);
    }

    public void sendEmail(BaseMail baseMail, String email, String password) {
        mailService.sendEmail(baseMail, email, password);
    }
}
