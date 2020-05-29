package ucll.be.todo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ucll.be.todo.converter.StringToLocalDateTime;

import java.time.LocalDateTime;

@SpringBootTest
public class StringToLocalDateTimeTest {
    private StringToLocalDateTime stringToLocalDateTime = new StringToLocalDateTime();

    @Test
    public void testConvert(){
        // setup
        String pattern = "01-12-1996 23:00";
        LocalDateTime time = LocalDateTime.of(1996,12,1,23,0);

        // checking method "convert"
        assertEquals(time,stringToLocalDateTime.convert(pattern));
    }
}
