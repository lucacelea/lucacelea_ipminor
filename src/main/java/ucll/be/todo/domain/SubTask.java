package ucll.be.todo.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class SubTask {
    @NotEmpty(message = "{TaskTitleEmpty}")
    private String title;
    @NotEmpty(message = "{TaskDescriptionEmpty}")
    private String description;
    @Id @GeneratedValue
    private int ID;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Task task;

    public SubTask(){
    }

    public SubTask(String title, String description){
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

    private void setID(int ID) {
        this.ID = ID;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
