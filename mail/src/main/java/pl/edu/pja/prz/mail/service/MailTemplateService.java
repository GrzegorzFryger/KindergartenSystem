package pl.edu.pja.prz.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;
import java.util.Map;

@Component
public class MailTemplateService {
	private Locale LOCALE = Locale.ENGLISH;
	private final SpringTemplateEngine templateEngine;

	@Autowired
	public MailTemplateService(SpringTemplateEngine templateEngine) {
		this.templateEngine = templateEngine;
	}

	public String assignEmailTemplate(String templateName, Map<String,Object> data ) {
		final Context ctx = new Context(LOCALE);
		data.forEach(ctx::setVariable);
		return templateEngine.process(templateName,ctx);
	}
}
