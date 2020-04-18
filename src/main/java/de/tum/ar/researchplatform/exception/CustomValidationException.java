package de.tum.ar.researchplatform.exception;

/**
 * Created by karthik on 4/18/2020
 */
public class CustomValidationException extends Exception {
    public CustomValidationException(String errorMessage) {
        super(errorMessage);
    }
}
