package ucll.be.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.be.todo.domain.Task;
import ucll.be.todo.repository.TaskRepository;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task getTaskByID(int id){
        return taskRepository.getOne(id);
    }

}
