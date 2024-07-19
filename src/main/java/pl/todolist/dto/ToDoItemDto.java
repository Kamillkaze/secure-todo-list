package pl.todolist.dto;

import lombok.*;
import pl.todolist.model.ToDoItem;

import java.sql.Date;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class ToDoItemDto {

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
