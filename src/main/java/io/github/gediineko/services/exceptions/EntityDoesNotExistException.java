package io.github.gediineko.services.exceptions;

public class EntityDoesNotExistException extends BaseException {

    private static final long serialVersionUID = -4202518367237468224L;

    public EntityDoesNotExistException(String message, String messageCode, Object[] args) {
        super(message, messageCode, args);
    }
}
