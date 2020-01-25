package pl.edu.pja.prz.mail.service;

import pl.edu.pja.prz.mail.model.BaseMailDTO;

public interface MailService {

    boolean sendEmail(String email, BaseMailDTO dto);

}
