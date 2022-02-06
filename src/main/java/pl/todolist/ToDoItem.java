package pl.todolist;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "to_do_items") //'-items todoitem0_'   'to-do-items todoitem0_'
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

    private String details;

    @NonNull
    private Date deadline;

    @NonNull
    private String shortDescription;
}
