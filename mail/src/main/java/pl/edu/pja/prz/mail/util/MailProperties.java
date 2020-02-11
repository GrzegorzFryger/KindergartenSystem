package pl.edu.pja.prz.mail.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailProperties {
    @Value("${spring.mail.host}")
    private String host;
    @Value("${spring.mail.port}")
    private String port;
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String password;
    @Value("${spring.mail.protocol}")
    private String protocol;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean smtpAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean smtpStartTls;
    @Value("${spring.mail.properties.mail.debug}")
    private boolean debug;
    @Value("${spring.mail.properties.mail.smtp.connectiontimeout}")
    private String smtpConnectionTimeout;
    @Value("${spring.mail.properties.mail.smtp.timeout}")
    private String smtpTimeout;
    @Value("${spring.mail.properties.mail.smtp.writetimeout}")
    private String smtpwWitetimeout;

    public MailProperties() {
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public boolean isSmtpAuth() {
        return smtpAuth;
    }

    public void setSmtpAuth(boolean smtpAuth) {
        this.smtpAuth = smtpAuth;
    }

    public boolean isSmtpStartTls() {
        return smtpStartTls;
    }

    public void setSmtpStartTls(boolean smtpStartTls) {
        this.smtpStartTls = smtpStartTls;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public String getSmtpConnectionTimeout() {
        return smtpConnectionTimeout;
    }

    public void setSmtpConnectionTimeout(String smtpConnectionTimeout) {
        this.smtpConnectionTimeout = smtpConnectionTimeout;
    }

    public String getSmtpTimeout() {
        return smtpTimeout;
    }

    public void setSmtpTimeout(String smtpTimeout) {
        this.smtpTimeout = smtpTimeout;
    }

    public String getSmtpwWitetimeout() {
        return smtpwWitetimeout;
    }

    public void setSmtpwWitetimeout(String smtpwWitetimeout) {
        this.smtpwWitetimeout = smtpwWitetimeout;
    }

    @Override
    public String toString() {
        return "MailProperties{" +
                "host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", protocol='" + protocol + '\'' +
                ", smtpAuth=" + smtpAuth +
                ", smtpStartTls=" + smtpStartTls +
                ", debug=" + debug +
                ", smtpConnectionTimeout='" + smtpConnectionTimeout + '\'' +
                ", smtpTimeout='" + smtpTimeout + '\'' +
                ", smtpwWitetimeout='" + smtpwWitetimeout + '\'' +
                '}';
    }
}
