package com.akmal.socialmediaapi.exception.handler;

import com.akmal.socialmediaapi.exception.AccessDeniedException;
import com.akmal.socialmediaapi.exception.BadRequestException;
import com.akmal.socialmediaapi.exception.NotFriendsException;
import com.akmal.socialmediaapi.util.payload.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({NotFriendsException.class})
    public ResponseEntity<ApiExceptionResponse> handleNotFriendsException(NotFriendsException e) {
        return new ResponseEntity<>(
                new ApiExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<ApiExceptionResponse> handleBadRequestException(BadRequestException e) {
        return new ResponseEntity<>(
                new ApiExceptionResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ApiExceptionResponse> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(
                new ApiExceptionResponse(e.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ApiExceptionResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        return new ResponseEntity<>(
                new ApiExceptionResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
