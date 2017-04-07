package de.mexchange.packagingdb.common.exception;

/**
 * Created by Arthur on 7/13/2016.
 */
public class InvalidParameterException extends InternalErrorException {

    public InvalidParameterException() {
    }

    public InvalidParameterException(String message) {
        super(message);
    }

    public InvalidParameterException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidParameterException(Throwable cause) {
        super(cause);
    }
}
