package de.mexchange.packagingdb.service.notification;

import de.mexchange.packagingdb.common.exception.NotificationException;
import de.mexchange.packagingdb.domain.User;
import de.mexchange.packagingdb.service.notification.util.EmailNotificationEvent;

/**
 * Created by Serozh on 6/4/2016.
 */
public interface EmailNotificationService {

    public void sendEmail(final String email, final EmailNotificationEvent event) throws NotificationException ;
    public void sendEmail(final User user,String url, final EmailNotificationEvent event) throws NotificationException ;
}
