package pl.todolist.model;

import lombok.*;
import pl.todolist.dto.ToDoItemDto;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "to_do_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class ToDoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private String shortDescription;

    private String details;

    @NonNull
    private Date deadline;

    public ToDoItem(String shortDescription, String details, Date deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.deadline = deadline;
    }

    public ToDoItem(ToDoItemDto item) {
        this.id = item.getId();
        this.shortDescription = item.getShortDescription();
        this.details = item.getDetails();
        this.deadline = item.getDeadline();
    }
}
