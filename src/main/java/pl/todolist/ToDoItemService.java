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

    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public List<ToDoItemDto> getAllToDoItems() {
        List<ToDoItemDto> toDoItems = toDoItemRepository.findAll()
                                    .stream()
                                    .sorted(Comparator.comparing(ToDoItem::getDeadline))
                                    .map(ToDoItemDto::new)
                                    .collect(Collectors.toList());
        return toDoItems;
    }

    public ToDoItemDto addToDoItem(ToDoItemDto item) {
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findByShortDescription(item.getShortDescription());

        if (toDoItemFromDB.isPresent()) {
            return null;
        }
        ToDoItem toBeSaved = new ToDoItem(item);
        ToDoItem saved = toDoItemRepository.save(toBeSaved);
        return new ToDoItemDto(saved);
    }

    public ToDoItemDto deleteToDoItem(ToDoItemDto item) {
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findByShortDescription(item.getShortDescription());

        if (toDoItemFromDB.isPresent()) {
            toDoItemRepository.delete(toDoItemFromDB.get());
            return new ToDoItemDto(toDoItemFromDB.get());
        }
        return null;
    }

    public ToDoItemDto updateToDoItem(int id, ToDoItemDto update){
        Optional<ToDoItem> toDoItemFromDB = toDoItemRepository.findById(id);

        if (toDoItemFromDB.isPresent()) {
            ToDoItem item = toDoItemFromDB.get();

            item.setShortDescription(update.getShortDescription());
            item.setDetails(update.getDetails());
            item.setDeadline(update.getDeadline());

            toDoItemRepository.save(item);
            return new ToDoItemDto(item);
        }
        return null;
    }
}
