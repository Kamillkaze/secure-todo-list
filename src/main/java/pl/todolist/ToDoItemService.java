package pl.todolist;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ToDoItemService {

    @Autowired
    ToDoItemRepository toDoItemRepository;


    public List<ToDoItem> getAllToDoItems() {
        List<ToDoItem> toDoItems = toDoItemRepository.findAll()
                                    .stream()
                                    .sorted(Comparator.comparing(ToDoItem::getDeadline))
                                    .collect(Collectors.toList());
        return toDoItems;
    }

    public ToDoItem addToDoItem(ToDoItem item) {
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findByShortDescription(item.getShortDescription());

        if (toDoItemFromDB.isPresent()) {
            return null;
        }
        ToDoItem saved = toDoItemRepository.save(item);
        return saved;
    }

    public ToDoItem deleteToDoItem(ToDoItem item) {
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findByShortDescription(item.getShortDescription());

        if (toDoItemFromDB.isPresent()) {
            toDoItemRepository.delete(toDoItemFromDB.get());
            return toDoItemFromDB.get();
        }
        return null;
    }

    public ToDoItem updateToDoItem(int id, ToDoItem update){
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findById(id);

        if (toDoItemFromDB.isPresent()) {
            ToDoItem item = toDoItemFromDB.get();

            item.setShortDescription(update.getShortDescription());
            item.setDetails(update.getDetails());
            item.setDeadline(update.getDeadline());

            toDoItemRepository.save(item);
            return item;
        }
        return null;
    }
}
