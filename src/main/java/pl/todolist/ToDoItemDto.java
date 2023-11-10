package pl.todolist;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class ToDoItemDto {

    @NonNull
    private int id;

    @NonNull
    private String shortDescription;

    private String details;

    @NonNull
    private Date deadline;

    public ToDoItemDto(ToDoItem item) {
        this.id = item.getId();
        this.shortDescription = item.getShortDescription();
        this.details = item.getDetails();
        this.deadline = item.getDeadline();
    }
}
