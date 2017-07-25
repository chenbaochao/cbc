package com.cbc.exception.core;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbc on 2017/7/21.
 */
public class ValidationException extends RuntimeException {

    private List<ValidationError> validationErrors = new ArrayList<ValidationError>();

    public ValidationException(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public ValidationException(ValidationError validationError){
        validationErrors.add(validationError);
    }

    public List<ValidationError> getValidationError(){
        return validationErrors;
    }

    @Override
    public String toString() {
        return "ValidationException{" +
                "validationErrors=" + validationErrors +
                '}';
    }

}
