package com.web.bean;

/**
 * Created by duyle on 16/02/2017.
 */
public class ErrorException extends Exception{

    private static final long serialVersionUID = 1L;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
    public ErrorException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    public ErrorException() {
        super();
    }

}
