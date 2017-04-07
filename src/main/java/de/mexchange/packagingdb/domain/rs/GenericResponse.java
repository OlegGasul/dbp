package de.mexchange.packagingdb.domain.rs;

import java.io.Serializable;

/**
 * Serializable object representing request status and message combination.
 */
public class GenericResponse implements Serializable {

    private int status;
    private String message;

    public GenericResponse() {
    }

    public GenericResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}