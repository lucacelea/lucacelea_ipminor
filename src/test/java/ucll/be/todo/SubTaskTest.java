package ucll.be.todo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.todo.domain.SubTask;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SubTaskTest {

    @Test
    public void testCreation(){
        // setup
        LocalDateTime localDateTime = LocalDateTime.of(1996,4,7,0,0);
        String title = "testT";
        String desc = "testD";

        // testing constructor
        SubTask subTask = new SubTask(title,desc);

        // checking
        assertEquals(subTask.getDescription(),desc);
        assertEquals(subTask.getTitle(),title);
    }
}
