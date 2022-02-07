package pl.todolist;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ToDoItemService {

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/todoitems")
    public ResponseEntity getToDoItems() throws JsonProcessingException {
        List<ToDoItem> toDoItems = toDoItemRepository.findAll()
                                    .stream()
                                    .sorted(Comparator.comparing(ToDoItem::getDeadline))
                                    .collect(Collectors.toList());
        return ResponseEntity.ok(objectMapper.writeValueAsString(toDoItems));
    }
}
