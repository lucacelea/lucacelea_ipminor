package ucll.be.todo.repository;

import org.springframework.stereotype.Repository;
import ucll.be.todo.domain.Task;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepo{
    private List<Task> tasks;

    public TaskRepo(){
        this.tasks = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.of(1996,Month.APRIL,1,1,1);
        tasks.add(new Task("Test",localDateTime,"leuke omschrijving"));
        tasks.add(new Task("Test1",localDateTime,"leuke omschrijving"));
        tasks.add(new Task("Test2",localDateTime,"leuke omschrijving"));
        tasks.add(new Task("Test3",localDateTime,"leuke omschrijving"));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        if (task == null) throw new IllegalArgumentException("Task kan niet null zijn.");
        for (Task t: tasks
             ) {
            if (t.getID() == task.getID()){
                throw new IllegalArgumentException("There cannot be tasks with duplicate IDs.");
            }
        }
        this.tasks.add(task);
    }

    public Task getTaskByID(int id) throws Exception {
        for (Task task:tasks
             ) {
            if (task.getID() == id){
                return task;
            }
        }
        throw new Exception("Task is not found with id: " + id);
    }
}
