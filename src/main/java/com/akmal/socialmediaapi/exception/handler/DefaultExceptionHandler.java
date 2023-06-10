package com.akmal.socialmediaapi.exception.handler;

import com.akmal.socialmediaapi.exception.AccessDeniedException;
import com.akmal.socialmediaapi.exception.BadRequestException;
import com.akmal.socialmediaapi.exception.NotFriendsException;
import com.akmal.socialmediaapi.payload.ApiExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

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
