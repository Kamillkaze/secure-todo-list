package pl.todolist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomIdException.class)
    public ResponseEntity<Object> handleCustomIdException(CustomIdException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);

        return new ResponseEntity<>(apiException, apiException.getStatus());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(apiException, apiException.getStatus());
    }
}
