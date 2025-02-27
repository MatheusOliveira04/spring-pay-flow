package git.MatheusOliveira04.controllers.exceptions;

import git.MatheusOliveira04.services.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> getObjectNotFoundException(ObjectNotFoundException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                        request.getRequestURI(), Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> getDataIntegrityViolationException(DataIntegrityViolationException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.CONFLICT.value(), request.getRequestURI(),
                        Collections.singletonList(exception.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> getMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), request.getRequestURI(),
                        exception.getAllErrors()
                                .stream()
                                .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
                                .toList()));
    }
}
