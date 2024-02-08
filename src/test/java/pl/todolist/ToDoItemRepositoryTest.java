package pl.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.todolist.model.ToDoItem;
import pl.todolist.repository.ToDoItemRepository;

import java.sql.Date;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class ToDoItemRepositoryTest {

    @Autowired
    private ToDoItemRepository underTest;

    @Test
    void existsByShortDescription() {
        
        Date testDate = new Date(1000000);
        ToDoItem item = new ToDoItem("Test item", "Used only for testing", testDate);
        underTest.save(item);

        boolean result = underTest.existsByShortDescription(item.getShortDescription());

        assertThat(result).isEqualTo(true);
    }

    @Test
    void existsById() {
        Date testDate = new Date(1000000);
        ToDoItem item = new ToDoItem("Test item", "Used only for testing", testDate);
        underTest.save(item);

        boolean existsById = underTest.existsById(item.getId());

        assertThat(existsById).isEqualTo(true);
    }
}