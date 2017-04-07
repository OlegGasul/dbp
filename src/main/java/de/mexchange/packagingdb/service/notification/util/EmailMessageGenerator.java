package de.mexchange.packagingdb.service.notification.util;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.service.MessageService;

@Component
public class EmailMessageGenerator {

    @Autowired
    private MessageService messageService;

    @Autowired
    private VelocityEngine velocityEngine;

    public static final String DEFAULT_ENCODING = "UTF-8";

    public String getContent(EmailNotificationEvent event) {
        if (event == null) {
            throw new InvalidParameterException("Invalid email event");
        }
        String message;
        switch (event) {
        case ACTIVATE_ACCOUNT:
            message = contentActivateAccount(null, null, null, null, null);
            break;

        default:
            throw new RuntimeException("template not found");
        }
        return message;
    }

    public String getContent(EmailNotificationEvent event, User user, String url) {
        if (event == null) {
            throw new InvalidParameterException("Invalid email event");
        }
        String message;
        String msg;
        String activation;
        String template;

        switch (event) {
        case ACTIVATE_ACCOUNT:
            msg = messageService.getMessage("mail.message.account.activation.request");
            activation = messageService.getMessage("mail.subject.account.activation");
            template = EmailNotificationEvent.ACTIVATE_ACCOUNT.getTemplate();
            message = contentActivateAccount(user, url, msg, activation, template);
            break;
        case FORGET_PASSWORD:
            msg = messageService.getMessage("mail.message.forget.password.request");
            activation = messageService.getMessage("mail.subject.forget.password");
            template = EmailNotificationEvent.FORGET_PASSWORD.getTemplate();
            message = contentActivateAccount(user, url, msg, activation, template);
            break;

        default:
            throw new RuntimeException("template not found");
        }
        return message;
    }

    protected String contentActivateAccount(User user, String url, String message, String activation, String template) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("message", message);
        model.put("activation", activation);
        model.put("url", url);
        model.put("user", user);
        return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, template, DEFAULT_ENCODING, model);
    }
}
