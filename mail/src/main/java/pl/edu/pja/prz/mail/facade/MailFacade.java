package pl.edu.pja.prz.mail.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.service.MailServiceImpl;

@Service
public class MailFacade {
    private final MailServiceImpl mailService;

    @Autowired
    public MailFacade(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    public boolean sendEmail(String email, BaseMail dto) {
        return false;
    }

}
