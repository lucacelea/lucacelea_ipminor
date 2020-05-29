package ucll.be.todo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.todo.domain.Task;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {

    @Test
    public void testCreation(){
        // setup
        LocalDateTime localDateTime = LocalDateTime.of(1996,4,7,0,0);
        String title = "testT";
        String desc = "testD";

        // testing constructor
        Task task = new Task(title, localDateTime,desc);

        // checking
        assertEquals(task.getDescription(),desc);
        assertEquals(task.getTitle(),title);
        assertEquals(task.getLocalDateTime(),localDateTime);
    }
}
