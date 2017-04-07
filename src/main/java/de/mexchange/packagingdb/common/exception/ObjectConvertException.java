package de.mexchange.packagingdb.common.exception;

/**
 * Created by Arthur on 7/13/2016.
 */
public class ObjectConvertException extends InternalErrorException {

    public ObjectConvertException() {
    }

    public ObjectConvertException(String message) {
        super(message);
    }

    public ObjectConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectConvertException(Throwable cause) {
        super(cause);
    }
}
