package de.mexchange.packagingdb.service.notification.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {

    @Value("${mail.debug}")
    private String debug;

    @Value("${mail.sender.email}")
    private String senderEmail;

    @Value("${mail.sender.name}")
    private String senderName;

    @Value("${mail.smtp.auth}")
    private String useAuthentication;

    @Value("${mail.smtp.login}")
    private String login;

    @Value("${mail.smtp.ssl}")
    private boolean ssl;

    @Value("${mail.smtp.password}")
    private String password;

    @Value("${mail.transport.protocol}")
    private String protocol;

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private int port;

    @Value("${mail.smtp.starttls.enable}")
    private boolean startTLSEnabled;

    @Value("${mail.smtp.starttls.required}")
    private boolean startTLSRequired;

    @Value("${mail.smtp.socketFactory.port}")
    private String socketFactoryPort;

    @Value("${mail.smtp.socketFactory.class}")
    private String socketFactoryClass;

    @Value("${mail.smtp.socketFactory.fallback}")
    private String socketFactoryFallback;

    @Value("${mail.to}")
    private String mailTo;

    public String getDebug() {
        return debug;
    }

    public void setDebug(String debug) {
        this.debug = debug;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getUseAuthentication() {
        return useAuthentication;
    }

    public void setUseAuthentication(String useAuthentication) {
        this.useAuthentication = useAuthentication;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isStartTLSEnabled() {
        return startTLSEnabled;
    }

    public void setStartTLSEnabled(boolean startTLSEnabled) {
        this.startTLSEnabled = startTLSEnabled;
    }

    public boolean isStartTLSRequired() {
        return startTLSRequired;
    }

    public void setStartTLSRequired(boolean startTLSRequired) {
        this.startTLSRequired = startTLSRequired;
    }

    public String getSocketFactoryPort() {
        return socketFactoryPort;
    }

    public void setSocketFactoryPort(String socketFactoryPort) {
        this.socketFactoryPort = socketFactoryPort;
    }

    public String getSocketFactoryClass() {
        return socketFactoryClass;
    }

    public void setSocketFactoryClass(String socketFactoryClass) {
        this.socketFactoryClass = socketFactoryClass;
    }

    public String getSocketFactoryFallback() {
        return socketFactoryFallback;
    }

    public void setSocketFactoryFallback(String socketFactoryFallback) {
        this.socketFactoryFallback = socketFactoryFallback;
    }

    public String getMailTo() {
        return mailTo;
    }

    @Bean(name = "mailSender")
    public JavaMailSenderImpl javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.debug", debug);
        mailProperties.put("mail.smtp.auth", useAuthentication);
        mailProperties.put("mail.smtp.ssl", ssl);
        mailProperties.put("mail.smtp.socketFactory.port", socketFactoryPort);
        mailProperties.put("mail.smtp.socketFactory.class", socketFactoryClass);
        mailProperties.put("mail.smtp.socketFactory.fallback", socketFactoryFallback);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(login);
        mailSender.setPassword(password);
        return mailSender;
    }

    @Override
    public String toString() {
        return "EmailConfig{" +
                "debug='" + debug + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", senderName='" + senderName + '\'' +
                ", useAuthentication='" + useAuthentication + '\'' +
                ", login='" + login + '\'' +
                ", ssl=" + ssl +
                ", password='" + password + '\'' +
                ", protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", startTLSEnabled=" + startTLSEnabled +
                ", startTLSRequired=" + startTLSRequired +
                ", socketFactoryPort='" + socketFactoryPort + '\'' +
                ", socketFactoryClass='" + socketFactoryClass + '\'' +
                ", socketFactoryFallback='" + socketFactoryFallback + '\'' +
                ", mailTo='" + mailTo + '\'' +
                '}';
    }
}
