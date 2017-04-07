package de.mexchange.packagingdb.service.notification.impl;

import de.mexchange.packagingdb.common.exception.NotificationException;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.service.MessageService;
import de.mexchange.packagingdb.service.notification.EmailNotificationService;
import de.mexchange.packagingdb.service.notification.config.EmailConfig;
import de.mexchange.packagingdb.service.notification.util.EmailMessageGenerator;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
public class SimpleEmailNotificationService implements EmailNotificationService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleEmailNotificationService.class);

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Autowired
    private EmailMessageGenerator emailMessageGenerator;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private MessageService messageService;

    /**
     * Executes initialization logic.
     */
    @PostConstruct
    private void init() {
        LOG.info("Email Notification service manager initialized.");
        if (LOG.isDebugEnabled()) {
            LOG.debug(emailConfig.toString());
        }
    }

    public void sendEmail(final String email, final EmailNotificationEvent event) throws NotificationException {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(email);
                    message.setFrom(emailConfig.getSenderEmail(), emailConfig.getSenderName());
                    message.setSubject(messageService.getMessage(event.getTitle()));
                    String content = emailMessageGenerator.getContent(event);
                    message.setText(content, true);
                }
            };
            mailSender.send(preparator);
        } catch (Exception e) {
            throw new NotificationException(e);
        }
    }

    public void sendEmail(final User user, final String url, final EmailNotificationEvent event) throws NotificationException {
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
                    message.setTo(user.getEmail());
                    message.setFrom(emailConfig.getSenderEmail(), emailConfig.getSenderName());
                    message.setSubject(messageService.getMessage(event.getTitle()));
                    String content = emailMessageGenerator.getContent(event, user, url);
                    message.setText(content, true);
                }
            };
            mailSender.send(preparator);
        } catch (Exception e) {
            throw new NotificationException(e);
        }
    }
}
