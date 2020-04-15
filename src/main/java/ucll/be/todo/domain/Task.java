package ucll.be.todo.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Task {
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime localDateTime;
    @NotEmpty(message = "Description cannot be empty.")
    private String description;
    @OneToMany(mappedBy = "task")
    private List<SubTask> subTasks;
    @Id @GeneratedValue
    private int ID;

    public Task(){
        subTasks = new ArrayList<>();
    }

    public Task(String title, LocalDateTime localDateTime, String description){
        setTitle(title);
        setLocalDateTime(localDateTime);
        setDescription(description);
        subTasks = new ArrayList<>();
    }

    private void setID(int ID) {
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

    public List<SubTask> getSubTasks() {
        return subTasks;
    }

    public void addSubTask(SubTask subTask) {
        this.subTasks.add(subTask);
    }

    public void removeSubTaskWithID(int id){
        subTasks.removeIf(t -> t.getID() == id);
    }
}
