package de.mexchange.packagingdb.common.exception;

/**
 * Created by Serozh on 6/4/2016.
 */
public class NotificationException extends Exception {

    public NotificationException() {
    }

    public NotificationException(String message) {
        super(message);
    }

    public NotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotificationException(Throwable cause) {
        super(cause);
    }
}
