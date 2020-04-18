package de.tum.ar.researchplatform.exception;

/**
 * Created by karthik on 4/18/2020
 */
public class CustomNotFoundException extends Exception {
    public CustomNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
