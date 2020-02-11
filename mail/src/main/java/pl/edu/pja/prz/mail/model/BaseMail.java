package pl.edu.pja.prz.mail.model;

import org.springframework.core.io.InputStreamSource;

import java.util.HashMap;
import java.util.Map;

public class BaseMail {
    private String to;
    private String subject;
    private String content;
    private Map<String, InputStreamSource> attachments = new HashMap<>();

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Map<String, InputStreamSource> getAttachments() {
        return attachments;
    }

    public void setAttachments(Map<String, InputStreamSource> attachments) {
        this.attachments = attachments;
    }

    @Override
    public String toString() {
        return "BaseMail{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
