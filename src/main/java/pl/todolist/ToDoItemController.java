package pl.todolist;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToDoItemController {

    private final ToDoItemService toDoItemService;

    public ToDoItemController(ToDoItemService toDoItemService) {
        this.toDoItemService = toDoItemService;
    }

    @GetMapping("/todoitems")
    public ResponseEntity<List<ToDoItemDto>> getToDoItems() {
        List<ToDoItemDto> toDoItems = toDoItemService.getAllToDoItems();

        return ResponseEntity.ok(toDoItems);
    }

    @PostMapping("/todoitems")
    public ResponseEntity<ToDoItemDto> addToDoItem(@RequestBody ToDoItemDto item) {
        ToDoItemDto toDoItem = toDoItemService.addToDoItem(item);

        if (toDoItem == null) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }

        return ResponseEntity.ok(toDoItem);
    }

    @DeleteMapping("/todoitems")
    public ResponseEntity<ToDoItemDto> deleteToDoItem(@RequestBody ToDoItemDto item) {
        ToDoItemDto deleted = toDoItemService.deleteToDoItem(item);

        if (deleted != null) {
            return ResponseEntity.ok(deleted);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/todoitems/{id}")
    public ResponseEntity<ToDoItemDto> updateToDoItem(@PathVariable(value = "id") int id, @RequestBody ToDoItemDto update){
        ToDoItemDto updated = toDoItemService.updateToDoItem(id, update);

        if (updated != null) {
            return ResponseEntity.ok(updated);
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }
}
