package ucll.be.todo.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateTime implements Converter<String, LocalDateTime> {
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s,formatterDate);
    }
}