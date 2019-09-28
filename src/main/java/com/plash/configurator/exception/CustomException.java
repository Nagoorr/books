package com.plash.configurator.exception;

import org.springframework.stereotype.Component;

/**
 * Created by Utsav on 18-Feb-17.
 */
@Component
public class CustomException extends Exception{

    //private static final long serialVersionUID = 1L;
    private int code;
    private String message;

   // @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


