package com.web.bean;

/**
 * Created by duyle on 16/02/2017.
 */
public class ResultException extends Exception {

    private static final long serialVersionUID = 1L;
    private String resultMessage;

    public String getResultMessage() {
        return resultMessage;
    }

    public ResultException(String errorMessage) {
        super(errorMessage);
        this.resultMessage = errorMessage;
    }

    public ResultException() {
        super();
    }


}
