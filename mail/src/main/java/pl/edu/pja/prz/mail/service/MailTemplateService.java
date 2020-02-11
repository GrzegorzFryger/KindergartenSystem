package pl.edu.pja.prz.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Map;

@Component
public class MailTemplateService {
    private final Context context;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public MailTemplateService(Context context, SpringTemplateEngine templateEngine) {
        this.context = context;
        this.templateEngine = templateEngine;
    }

    public String assignEmailTemplate(String templateName, Map<String, Object> data) {
        data.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }
}
