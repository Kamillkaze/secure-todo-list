package pl.todolist.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ApiException {
    private final String message;
    private final HttpStatus status;
}
