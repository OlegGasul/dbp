package de.mexchange.packagingdb.service.notification.util;

/**
 * Created by Serozh on 6/4/2016.
 */
public enum EmailNotificationEvent {

    ACTIVATE_ACCOUNT("mail.subject.account.activation", "profile-activation.vm"),
    FORGET_PASSWORD("mail.subject.forget.password", "profile-activation.vm"),;

    EmailNotificationEvent(String title, String template) {
        this.template = template;
        this.title = title;
    }

    private String title;

    private String template;

    public String getTitle() {
        return title;
    }

    public String getTemplate() {
        return template;
    }
}
