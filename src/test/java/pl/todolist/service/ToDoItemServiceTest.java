package pl.todolist.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.todolist.dto.ToDoItemDto;
import pl.todolist.exception.CustomIdException;
import pl.todolist.model.ToDoItem;
import pl.todolist.repository.ToDoItemRepository;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.*;
import static pl.todolist.utils.TestUtils.getDefaultClientsList;
import static pl.todolist.utils.TestUtils.getDefaultClientsListDto;

@ExtendWith(MockitoExtension.class)
class ToDoItemServiceTest {

    @Mock
    private ToDoItemRepository toDoItemRepositoryMock;

    @InjectMocks
    private ToDoItemService service;

    @Test
    @DisplayName("Should return an empty list of ToDoItemDto when repository returns empty list of ToDoItem")
    void getAllToDoItemsWhenRepositoryReturnsEmptyList() {
        when(toDoItemRepositoryMock.findAllByOrderByDeadline()).thenReturn(List.of());

        List<ToDoItemDto> allToDoItems = service.getAllToDoItems();

        verify(toDoItemRepositoryMock).findAllByOrderByDeadline();
        assertThat(allToDoItems).isEmpty();
    }

    @Test
    @DisplayName("Should return a list of ToDoItemDto mapped from list of ToDoItem returned by repository")
    void getAllToDoItemsWhenRepositoryReturnsValidList() {
        when(toDoItemRepositoryMock.findAllByOrderByDeadline()).thenReturn(getDefaultClientsList());
        List<ToDoItemDto> expected = getDefaultClientsListDto();

        List<ToDoItemDto> allToDoItems = service.getAllToDoItems();

        verify(toDoItemRepositoryMock).findAllByOrderByDeadline();
        assertThat(allToDoItems).containsAll(expected);
    }

    @Test
    @DisplayName("Should throw CustomIdException when item passed has id != 0")
    void addToDoItemWhenIdNotEqualsZero() {
        ToDoItemDto item = new ToDoItemDto(1, "", "", new Date(100000000));

        assertThatThrownBy(() -> service.addToDoItem(item))
                .isInstanceOf(CustomIdException.class)
                .hasMessage("Value of id is generated by the database, it cannot be passed to the server");
    }

    @Test
    @DisplayName("Should add a new item correctly")
    void addToDoItemWhenInputCorrect() {
        ToDoItemDto input = getDefaultClientsListDto().get(0);
        ToDoItem repoInput = new ToDoItem(input);
        ToDoItem repoResult = new ToDoItem(122, repoInput.getShortDescription(), repoInput.getDetails(), repoInput.getDeadline());
        when(toDoItemRepositoryMock.save(repoInput)).thenReturn(repoResult);

        ToDoItemDto result = service.addToDoItem(input);

        verify(toDoItemRepositoryMock, times(1)).save(repoInput);
        assertThat(result).isEqualTo(new ToDoItemDto(repoResult));
    }

    @Test
    @DisplayName("Should delete item correctly")
    void deleteToDoItemByIdCorrectly() {
        when(toDoItemRepositoryMock.deleteById(1)).thenReturn(1);

        service.deleteToDoItemById(1);

        verify(toDoItemRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when repository returns 0 as a number of deleted items")
    void deleteToDoItemByIdWhenRepoReturnsZero() {
        when(toDoItemRepositoryMock.deleteById(1)).thenReturn(0);

        assertThatThrownBy(() -> service.deleteToDoItemById(1))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with id = 1 does not exist.");
        verify(toDoItemRepositoryMock, times(1)).deleteById(1);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when trying to update not existing item")
    void updateToDoItemWhenItemDoesNotExist() {
        ToDoItemDto item = getDefaultClientsListDto().get(0);
        when(toDoItemRepositoryMock.existsById(item.getId())).thenReturn(false);

        assertThatThrownBy(() -> service.updateToDoItem(item.getId(), item))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with id = " + item.getId() + " does not exist.");
        verify(toDoItemRepositoryMock, times(1)).existsById(item.getId());
    }
    @Test
    @DisplayName("Should update item properly")
    void updateToDoItemWhenEverythingOK() {
        ToDoItem input = getDefaultClientsList().get(0);
        ToDoItemDto inputDto = getDefaultClientsListDto().get(0);
        ToDoItem fromRepo = new ToDoItem(123, "descr", "det", new Date(543123243));
        when(toDoItemRepositoryMock.save(input)).thenReturn(fromRepo);
        when(toDoItemRepositoryMock.existsById(inputDto.getId())).thenReturn(true);

        ToDoItemDto result = service.updateToDoItem(inputDto.getId(), inputDto);

        assertThat(result).isEqualTo(new ToDoItemDto(fromRepo));
        verify(toDoItemRepositoryMock, times(1)).existsById(inputDto.getId());
        verify(toDoItemRepositoryMock, times(1)).save(input);
    }
}