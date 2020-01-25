package pl.edu.pja.prz.mail.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMailDTO;
import pl.edu.pja.prz.mail.service.impl.MailServiceImpl;

@Service
public class MailFacade {
    private final MailServiceImpl mailService;

    @Autowired
    public MailFacade(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    public boolean sendEmail(String email, BaseMailDTO dto) {
        return mailService.sendEmail(email, dto);
    }

}
