package pl.todolist.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class ApiException {
    private final String message;
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
