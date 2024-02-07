package pl.todolist.exception;

import lombok.Getter;

@Getter
public class DuplicatedValueOfUniqueFieldException extends RuntimeException {
    private final String message;

    public DuplicatedValueOfUniqueFieldException(String duplicatedValue) {
        this.message = "Attempt to add already existing value to the column marked as unique. Existing value is: `" + duplicatedValue + "`";
    }
}
