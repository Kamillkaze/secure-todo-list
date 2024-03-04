package pl.todolist.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.todolist.exception.CustomIdException;
import pl.todolist.model.ToDoItem;
import pl.todolist.dto.ToDoItemDto;
import pl.todolist.repository.ToDoItemRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToDoItemService {

    private final ToDoItemRepository toDoItemRepository;

    public ToDoItemService(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    public List<ToDoItemDto> getAllToDoItems() {
        return toDoItemRepository.findAllByOrderByDeadline()
                                    .stream()
                                    .map(ToDoItemDto::new)
                                    .collect(Collectors.toList());
    }

    public ToDoItemDto addToDoItem(ToDoItemDto item) {
        if (item.getId() != 0) {
            throw new CustomIdException();
        }

        ToDoItem toBeSaved = new ToDoItem(item);
        ToDoItem saved = toDoItemRepository.save(toBeSaved);
        return new ToDoItemDto(saved);
    }

    @Transactional
    public void deleteToDoItemById(int id) {
        int numberOfDeletedItems = toDoItemRepository.deleteById(id);

        if (numberOfDeletedItems == 0) {
            throw new EntityNotFoundException("Entity with id = " + id + " does not exist.");
        }
    }

    public ToDoItemDto updateToDoItem(int id, ToDoItemDto update){
        if (!toDoItemRepository.existsById(id)) {
            throw new EntityNotFoundException("Entity with id = " + id + " does not exist.");
        }

        ToDoItem item = new ToDoItem(id, update.getShortDescription(), update.getDetails(), update.getDeadline());
        ToDoItem saved = toDoItemRepository.save(item);
        return new ToDoItemDto(saved);
    }
}
