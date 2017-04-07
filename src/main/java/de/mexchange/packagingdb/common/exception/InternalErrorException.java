package de.mexchange.packagingdb.common.exception;

/**
 * Internal Server Error exception class
 */
public class InternalErrorException extends Exception {

    public InternalErrorException() {
        super();
    }

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalErrorException(Throwable cause) {
        super(cause);
    }
}