package ucll.be.todo.service;

import javafx.scene.media.SubtitleTrack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ucll.be.todo.domain.SubTask;
import ucll.be.todo.domain.Task;
import ucll.be.todo.dto.SubTaskDTO;
import ucll.be.todo.dto.TaskDTO;
import ucll.be.todo.repository.SubTaskRepository;
import ucll.be.todo.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final SubTaskRepository subTaskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, SubTaskRepository subTaskRepository){
        this.taskRepository = taskRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    public List<TaskDTO> getTasks() {
        return taskRepository.findAll().stream().map(this::tasktoTaskDTO).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        taskRepository.save(taskDTOtoTask(taskDTO));
    }

    @Override
    public TaskDTO getTaskByID(int id){
        if(taskRepository.findById(id).isPresent()){
            Task task = taskRepository.findById(id).get();
            return tasktoTaskDTO(task);
        }
        return null;
    }

    @Override
    public void updateTask(TaskDTO taskDTO,Integer id) {
        if(taskRepository.findById(id).isPresent()){
            Task task = taskRepository.findById(id).get();
            task.setTitle(taskDTO.getTitle());
            task.setDescription(taskDTO.getDescription());
            task.setLocalDateTime(taskDTO.getLocalDateTime());
            for (SubTaskDTO t: taskDTO.getSubTasks()
            ) {
                SubTask subTask = new SubTask();
                subTask.setDescription(t.getDescription());
                subTask.setTitle(t.getTitle());
                subTask.setTask(t.getTask());
                task.addSubTask(subTask);
            }
            taskRepository.save(task);
        } else throw new IllegalArgumentException();
    }

    @Override
    public void addSubTask(SubTaskDTO subTaskDTO, Integer id) {
        if(taskRepository.findById(id).isPresent()){
            Task task = taskRepository.findById(id).get();
            SubTask subTask = new SubTask();
            subTask.setTask(task);
            subTask.setTitle(subTaskDTO.getTitle());
            subTask.setDescription(subTaskDTO.getDescription());
            subTaskRepository.save(subTask);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void removeSubTask(int idT, int idS){
            subTaskRepository.deleteById(idS);
    }

    public Task taskDTOtoTask(TaskDTO taskDTO){
        Task task = new Task();
        if (taskDTO.getSubTasks() != null){
            for (SubTaskDTO t: taskDTO.getSubTasks()
                 ) {
                SubTask subTask = new SubTask();
                subTask.setDescription(t.getDescription());
                subTask.setTitle(t.getTitle());
                subTask.setTask(t.getTask());
                task.addSubTask(subTask);
            }
        }
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setLocalDateTime(taskDTO.getLocalDateTime());
        return task;
    }

    public TaskDTO tasktoTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setID(task.getID());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setLocalDateTime(task.getLocalDateTime());
        taskDTO.setTitle(task.getTitle());
        for (SubTask t: task.getSubTasks()
             ) {
            SubTaskDTO subTaskDTO = new SubTaskDTO();
            subTaskDTO.setID(t.getID());
            subTaskDTO.setDescription(t.getDescription());
            subTaskDTO.setTitle(t.getTitle());
            subTaskDTO.setTask(t.getTask());
            taskDTO.addSubTask(subTaskDTO);
        }
        return taskDTO;
    }


}
