package pl.edu.pja.prz.mail.service;

import pl.edu.pja.prz.mail.model.BaseMail;

public interface MailService {

	boolean sendEmail(BaseMail baseMail);

	boolean sendEmail(String email, String password, BaseMail baseMail);

	boolean validateInput(BaseMail baseMail);
}
