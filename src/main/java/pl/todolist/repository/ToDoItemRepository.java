package pl.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.todolist.model.ToDoItem;

import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    boolean existsByShortDescription(String value);
    Optional<ToDoItem> findById(int id);
    @Modifying
    @Query("DELETE FROM ToDoItem t WHERE t.shortDescription = :shortDescription")
    int deleteByShortDescription(@Param(value = "shortDescription") String shortDescription);
}
