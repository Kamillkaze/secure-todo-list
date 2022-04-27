package pl.todolist;

import lombok.*;

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
    private String shortDescription;

    private String details;

    @NonNull
    private Date deadline;

    public ToDoItem(String shortDescription, String details, Date deadline) {
        this.shortDescription = shortDescription;
        this.details = details;
        this.deadline = deadline;
    }
}
