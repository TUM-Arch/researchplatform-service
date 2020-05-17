package de.tum.ar.researchplatform.exception;

/**
 * Created by karthik on 5/16/2020
 */
public class CustomLoginException extends Exception {
    public CustomLoginException(String errorMessage) {
        super(errorMessage);
    }
}
