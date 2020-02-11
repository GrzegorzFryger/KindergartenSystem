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

    public boolean sendEmail(BaseMail baseMail) {
        return mailService.sendEmail(baseMail);
    }

    public boolean sendEmail(String email, String password, BaseMail baseMail) {
        return mailService.sendEmail(email, password, baseMail);
    }
}
