package pl.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.todolist.model.ToDoItem;

import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    Optional<ToDoItem> findByShortDescription(String shortDescription);
    Optional<ToDoItem> findById(int id);
    long deleteByShortDescription(String shortDescription);
}
