package pl.todolist.utils;

import pl.todolist.dto.ToDoItemDto;
import pl.todolist.model.ToDoItem;

import java.sql.Date;
import java.util.Comparator;
import java.util.List;

public class TestUtils {
    private static final ToDoItemDto ITEM_1 = new ToDoItemDto(0,"description1", "details", new Date(100000000));
    private static final ToDoItemDto ITEM_2 = new ToDoItemDto(0,"description2", "details", new Date(200000000));
    private static final ToDoItemDto ITEM_3 = new ToDoItemDto(0,"description3", "details", new Date(300000000));
    private static final ToDoItemDto ITEM_4 = new ToDoItemDto(0,"description4", "details", new Date(400000000));
    private static final ToDoItemDto ITEM_5 = new ToDoItemDto(0,"description5", "details", new Date(500000000));

    public static List<ToDoItem> getDefaultClientsList() {
        return getDefaultClientsListDto()
                .stream()
                .map(ToDoItem::new)
                .sorted(Comparator.comparing(ToDoItem::getDeadline))
                .toList();
    }

    public static List<ToDoItemDto> getDefaultClientsListDto() {
        return List.of(ITEM_1, ITEM_2, ITEM_3, ITEM_4, ITEM_5);
    }
}
