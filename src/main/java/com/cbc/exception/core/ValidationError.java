package com.cbc.exception.core;

import org.springframework.validation.FieldError;

/**
 * Created by cbc on 2017/7/21.
 */
public class ValidationError {

    private String attributeName;
    private String message;

    public ValidationError() {

    }

    public ValidationError(final String attributeName, final String message) {
        this.attributeName = attributeName;
        this.message = message;
    }

    public ValidationError(FieldError err) {
        attributeName = err.getField();
        message = err.getDefaultMessage();
    }

    public String getExternalPropertyName() {
        return attributeName;
    }

    public String getMessage(){
        return message;
    }

    public void setExternalPropertyName(String attributeName){
        this.attributeName=attributeName;
    }

    public void setMessage(String message){
        this.message=message;
    }

    @Override
    public String toString(){
        return "ValidationError{" +
                "attributeName='" + attributeName + '\'' +
                ", message='"+ message + '\'' +
                '}';
    }
}
