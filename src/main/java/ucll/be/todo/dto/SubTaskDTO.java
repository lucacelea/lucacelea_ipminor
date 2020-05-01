package ucll.be.todo.dto;

import ucll.be.todo.domain.Task;
import javax.validation.constraints.NotEmpty;

public class SubTaskDTO {
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @NotEmpty(message = "Description cannot be empty.")
    private String description;
    private int ID;
    private Task task;

    public SubTaskDTO(){
    }

    public SubTaskDTO(String title, String description){
        setTitle(title);
        setDescription(description);
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
