package pl.edu.pja.prz.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pl.edu.pja.prz.mail.model.BaseMail;
import pl.edu.pja.prz.mail.util.JavaMailSenderFactory;

import javax.mail.MessagingException;

@Service
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	public JavaMailSenderFactory emailSenderFactory;

	@Override
	public void sendEmail(BaseMail dto) {
		emailSenderFactory.getSender().send(prepareMimeMessage(dto));
	}

	public void sendEmail(String email, String password, BaseMail dto) {
		emailSenderFactory.getSender(email, password).send(prepareMimeMessage(dto));
	}

	private MimeMessagePreparator prepareMimeMessage(BaseMail dto) {
		return mimeMessage -> {
			MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

			message.setTo(dto.getTo());
			message.setSubject(dto.getSubject());
			message.setText(dto.getContent(), true);

			if (dto.getAttachments().size() > 0) {
				dto.getAttachments()
						.forEach((key, attachment) -> {
									try {
										message.addAttachment(key, attachment);
									} catch (MessagingException e) {
										e.printStackTrace();
									}
								}

						);
			}
		};
	}


}




