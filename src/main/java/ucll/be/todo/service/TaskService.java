package ucll.be.todo.service;

import ucll.be.todo.dto.SubTaskDTO;
import ucll.be.todo.dto.TaskDTO;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getTasks();
    void addTask(TaskDTO taskDTO);
    TaskDTO getTaskByID(int id) throws Exception;
    void updateTask(TaskDTO taskDTO,Integer id);
    void addSubTask(SubTaskDTO subTaskDTO, Integer id);

    void removeSubTask(int idT, int idS);
}
