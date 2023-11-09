package pl.todolist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoItemController {

    @Autowired
    ToDoItemService toDoItemService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/todoitems")
    public ResponseEntity getToDoItems() throws JsonProcessingException {
        List<ToDoItem> toDoItems = toDoItemService.getAllToDoItems();

        return ResponseEntity.ok(objectMapper.writeValueAsString(toDoItems));
    }

    @PostMapping("/todoitems")
    public ResponseEntity addToDoItem(@RequestBody ToDoItem item) {
        ToDoItem toDoItem = toDoItemService.addToDoItem(item);

        if (toDoItem == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        return ResponseEntity.ok(toDoItem);
    }

    @DeleteMapping("/todoitems")
    public ResponseEntity deleteToDoItem(@RequestBody ToDoItem item) {
        ToDoItem deleted = toDoItemService.deleteToDoItem(item);

        if (deleted != null) {
            return ResponseEntity.ok(deleted);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/todoitems/{id}")
    public ResponseEntity updateToDoItem(@PathVariable(value = "id") int id, @RequestBody ToDoItem update){
        ToDoItem updated = toDoItemService.updateToDoItem(id, update);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
