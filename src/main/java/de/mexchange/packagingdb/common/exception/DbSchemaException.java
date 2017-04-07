package de.mexchange.packagingdb.common.exception;

/**
 * Created by Arthur on 5/16/2016.
 */
public class DbSchemaException extends Exception {

    public DbSchemaException() {
    }

    public DbSchemaException(String message) {
        super(message);
    }

    public DbSchemaException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbSchemaException(Throwable cause) {
        super(cause.getMessage(), cause);
    }
}
