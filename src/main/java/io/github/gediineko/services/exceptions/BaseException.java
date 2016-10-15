package io.github.gediineko.services.exceptions;

/**
 * Created by ggolong on 8/30/16.
 */
public abstract class BaseException extends RuntimeException {

    private String messageCode;
    private Object[] args;

    public BaseException(String message, String messageCode, Object[] args) {
        super(message);
        this.messageCode = messageCode;
        this.args = args;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

}
