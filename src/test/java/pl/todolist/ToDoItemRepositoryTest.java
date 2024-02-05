package pl.todolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pl.todolist.model.ToDoItem;
import pl.todolist.repository.ToDoItemRepository;

import java.sql.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
class ToDoItemRepositoryTest {

    @Autowired
    private ToDoItemRepository underTest;

    @Test
    void findByShortDescription() {
        
        Date testDate = new Date(1000000);
        ToDoItem item = new ToDoItem("Test item", "Used only for testing", testDate);
        underTest.save(item);

        Optional<ToDoItem> foundByShortDescription = underTest
                                                    .findByShortDescription(item.getShortDescription())
                                                    .or(() -> Optional.of(new ToDoItem()));

        assertThat(foundByShortDescription.get()).isEqualTo(item);
    }

    @Test
    void findById() {
        Date testDate = new Date(1000000);
        ToDoItem item = new ToDoItem("Test item", "Used only for testing", testDate);
        underTest.save(item);

        Optional<ToDoItem> foundById = underTest
                                        .findById(item.getId())
                                        .or(() -> Optional.of(new ToDoItem()));

        assertThat(foundById.get()).isEqualTo(item);
    }
}