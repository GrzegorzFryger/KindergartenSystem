package pl.edu.pja.prz.mail.model;

import org.springframework.core.io.InputStreamSource;
import pl.edu.pja.prz.mail.model.enums.EmailTemplate;

import java.util.HashMap;
import java.util.Map;

public class BaseMail {
    public static final String CONTENT_KEY = "content";

    private String to;
    private String subject;
    /**
     * This variable is used in basic template.<br>
     * If you have any specific needs - you should add fields with text to {@link #variables} map
     */
    private String content;
    private Map<String, InputStreamSource> attachments = new HashMap<>();
    private Map<String, Object> variables = new HashMap<>();
    private EmailTemplate emailTemplate = EmailTemplate.BASE_TEMPLATE;

    public BaseMail() {

    }

    public BaseMail(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, InputStreamSource> getAttachments() {
        return attachments;
    }

    public void addAttachment(String key, InputStreamSource value) {
        attachments.put(key, value);
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void addVariable(String key, Object value) {
        variables.put(key, value);
    }

    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplate emailTemplate) {
        this.emailTemplate = emailTemplate;
    }

    public void setAttachments(Map<String, InputStreamSource> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
