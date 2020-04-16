package ucll.be.todo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime localDateTime;
    @NotEmpty(message = "Description cannot be empty.")
    private String description;
    private List<SubTaskDTO> subTasks;
    @GeneratedValue
    private int ID;

    public TaskDTO(){
        subTasks = new ArrayList<>();
    }

    public TaskDTO(String title, LocalDateTime localDateTime, String description){
        setTitle(title);
        setLocalDateTime(localDateTime);
        setDescription(description);
        subTasks = new ArrayList<>();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getFormattedDate(){
        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

        return localDateTime.format(formatterDate) + " at " + localDateTime.format(formatterTime);
    }

    public List<SubTaskDTO> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTaskDTO subTask) {
        this.subTasks.add(subTask);
    }

    public void removeSubTaskWithID(int id){
        subTasks.removeIf(t -> t.getID() == id);
    }

    public void setSubTasks(List<SubTaskDTO> subTasks) {
        this.subTasks = subTasks;
    }
}
