package io.github.gediineko.services.exceptions;

/**
 * Created by ggolong on 8/30/16.
 */
public class InvalidFileTypeException extends BaseException {

    private static final long serialVersionUID = -5889396907316870353L;

    public InvalidFileTypeException(String message, String messageCode, Object[] args) {
        super(message, messageCode, args);
    }
}
