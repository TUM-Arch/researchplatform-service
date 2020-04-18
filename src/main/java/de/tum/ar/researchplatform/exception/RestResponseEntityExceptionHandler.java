package de.tum.ar.researchplatform.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by karthik on 4/18/2020
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    //400
    @ExceptionHandler({ CustomValidationException.class })
    public ResponseEntity<Object> handleCustomValidationException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Bad Request", new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    //401
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Unauthorized", new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    //404
    @ExceptionHandler({ CustomNotFoundException.class })
    public ResponseEntity<Object> handleCustomNotFoundException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    //500
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                "Internal Server Error", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
