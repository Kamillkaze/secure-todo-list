package pl.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    Optional<ToDoItem> findByShortDescription(String shortDescription);
    Optional<ToDoItem> findById(int id);
}
