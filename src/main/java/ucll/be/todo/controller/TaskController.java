package ucll.be.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ucll.be.todo.domain.SubTask;
import ucll.be.todo.domain.Task;
import ucll.be.todo.dto.SubTaskDTO;
import ucll.be.todo.dto.TaskDTO;
import ucll.be.todo.service.TaskService;

import javax.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public String showOverview(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        model.addAttribute("taskDTO", new TaskDTO());
        return "tasks";
    }

    @PostMapping("/new")
    public String addTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("tasks",taskService.getTasks());
            return "tasks";
        }
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id){
        try {
            model.addAttribute("task", taskService.getTaskByID(id));
            model.addAttribute("subTaskDTO",new SubTaskDTO());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "taskDetail";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(Model model, @PathVariable("id") Integer id){
        try {
            model.addAttribute("taskDTO", taskService.getTaskByID(id));
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@ModelAttribute @Valid TaskDTO taskDTO, BindingResult bindingResult, Model model, @PathVariable("id") Integer id){
        if (bindingResult.hasErrors()){
            return "editTask";
        }
        try {
            taskService.updateTask(taskDTO,id);
            model.addAttribute("task", taskService.getTaskByID(id));
            model.addAttribute("subTaskDTO",new SubTaskDTO());
            return "taskDetail";
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @PostMapping("/{id}/sub/create")
    public String addSubTask(@ModelAttribute @Valid SubTaskDTO subTaskDTO, BindingResult bindingResult, Model model, @PathVariable("id") Integer id){
        try {
            if (!bindingResult.hasErrors()) {
                taskService.addSubTask(subTaskDTO, id);
                model.addAttribute("subTask", new SubTaskDTO());
            }
            model.addAttribute("task", taskService.getTaskByID(id));
            return "taskDetail";
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @GetMapping("/{idT}/sub/remove/{idS}")
    public String removeSubTask(Model model, @PathVariable("idT") Integer idT,@PathVariable("idS") Integer idS){
        try {
            taskService.removeSubTask(idT,idS);
            model.addAttribute("task", taskService.getTaskByID(idT));
            model.addAttribute("subTaskDTO",new SubTaskDTO());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "taskDetail";
    }
}

