package com.akmal.socialmediaapi.exception.handler;


import com.akmal.socialmediaapi.payload.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
                new ApiExceptionResponse(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

}

