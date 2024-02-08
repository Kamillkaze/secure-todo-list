package pl.todolist.exception;

import lombok.Getter;

@Getter
public class CustomIdException extends RuntimeException {
    private final String message;

    public CustomIdException() {
        this.message = "Value of id is generated by the database, it cannot be passed to the server";
    }
}
