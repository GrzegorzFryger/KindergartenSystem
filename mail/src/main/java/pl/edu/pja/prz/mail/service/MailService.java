package pl.edu.pja.prz.mail.service;

import pl.edu.pja.prz.mail.model.BaseMail;

public interface MailService {

	void sendEmail(BaseMail baseMail);

	void sendEmail(BaseMail baseMail, String email, String password);

	boolean validateInput(BaseMail baseMail);
}
