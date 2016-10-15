package io.github.gediineko.services.exceptions;


public class EntityAlreadyExistsException extends BaseException {


    private static final long serialVersionUID = 6283512400143376243L;

    public EntityAlreadyExistsException(String message, String messageCode, Object[] args) {
        super(message, messageCode, args);
    }
}
