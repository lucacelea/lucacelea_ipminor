package ucll.be.todo.service;

import ucll.be.todo.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks();
    void addTask(Task task);
    Task getTaskByID(int id) throws Exception;
}
