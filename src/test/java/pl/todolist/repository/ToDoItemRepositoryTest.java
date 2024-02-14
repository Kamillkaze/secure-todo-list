package pl.todolist.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.todolist.model.ToDoItem;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static pl.todolist.utils.TestUtils.getDefaultClientsList;

@DataJpaTest
class ToDoItemRepositoryTest {

    @Autowired
    private ToDoItemRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("Should return true if item exists by id")
    void existsByIdIfExists() {
        ToDoItem saved = repository.save(getDefaultClientsList().get(3));

        boolean existsById = repository.existsById(saved.getId());

        assertThat(existsById).isTrue();
    }

    @Test
    @DisplayName("Should return false if item does not exist by id")
    void existsByIdIfDoesNotExist() {
        ToDoItem item = getDefaultClientsList().get(3);

        boolean existsById = repository.existsById(item.getId());

        assertThat(existsById).isFalse();
    }

    @Test
    @DisplayName("Should return 1 if an item exists by id")
    void deleteByIdWhenExists() {
        ToDoItem saved = repository.save(getDefaultClientsList().get(3));

        int numberOfDeletedItems = repository.deleteById(saved.getId());

        assertThat(numberOfDeletedItems).isEqualTo(1);
    }

    @Test
    @DisplayName("Should return 0 if an item does not exist by id")
    void deleteByIdWhenDoesNotExist() {
        ToDoItem item = getDefaultClientsList().get(3);

        int numberOfDeletedItems = repository.deleteById(item.getId());

        assertThat(numberOfDeletedItems).isEqualTo(0);
    }

    @Test
    @DisplayName("Should return an empty list if table empty")
    void findAllByOrderByDeadlineWhenEmptyTable() {
        List<ToDoItem> allItems = repository.findAllByOrderByDeadline();

        assertThat(allItems).isEmpty();
    }

    @Test
    @DisplayName("Should return a list of all items ordered ascending by deadline")
    void findAllByOrderByDeadline() {
        List<ToDoItem> defaultClients = getDefaultClientsList();
        ToDoItem item2 = repository.save(defaultClients.get(2));
        ToDoItem item0 = repository.save(defaultClients.get(0));
        ToDoItem item4 = repository.save(defaultClients.get(4));
        ToDoItem item3 = repository.save(defaultClients.get(3));
        ToDoItem item1 = repository.save(defaultClients.get(1));
        List<ToDoItem> expected = List.of(item0, item1, item2, item3, item4);

        List<ToDoItem> allByOrderByDeadline = repository.findAllByOrderByDeadline();

        assertThat(allByOrderByDeadline).containsAll(expected);
    }
}