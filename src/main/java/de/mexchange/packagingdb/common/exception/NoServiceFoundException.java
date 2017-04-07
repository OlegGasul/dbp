package de.mexchange.packagingdb.common.exception;

/**
 *
 */
public class NoServiceFoundException extends Exception {

	private Class clazz;

    public NoServiceFoundException() {
        super();
    }

    public NoServiceFoundException(Class clazz) {
        super();
		this.clazz = clazz;
    }

    public NoServiceFoundException(String message) {
        super(message);
    }

    public NoServiceFoundException(Class clazz, String message) {
        super(message);
		this.clazz = clazz;
    }

    public NoServiceFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoServiceFoundException(Class clazz, String message, Throwable cause) {
        super(message, cause);
		this.clazz = clazz;
    }

    public NoServiceFoundException(Throwable cause) {
        super(cause);
    }

    public NoServiceFoundException(Class clazz, Throwable cause) {
        super(cause);
		this.clazz = clazz;
    }

	public Class getClazz() {
		return clazz;
	}
}