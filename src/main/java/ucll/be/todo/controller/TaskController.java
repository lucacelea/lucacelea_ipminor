package ucll.be.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ucll.be.todo.domain.SubTask;
import ucll.be.todo.domain.Task;
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
        model.addAttribute("task", new Task());
        return "tasks";
    }

    @PostMapping("/new")
    public String addTask(@ModelAttribute @Valid Task task, BindingResult bindingResult,Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("tasks",taskService.getTasks());
            return "tasks";
        }
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}")
    public String showDetail(Model model, @PathVariable("id") Integer id){
        try {
            model.addAttribute("task", taskService.getTaskByID(id));
            model.addAttribute("subTask",new SubTask());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "taskDetail";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(Model model, @PathVariable("id") Integer id){
        try {
            model.addAttribute("task", taskService.getTaskByID(id));
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String editTask(@ModelAttribute @Valid Task task, BindingResult bindingResult, Model model, @PathVariable("id") Integer id){
        if (bindingResult.hasErrors()){
            return "editTask";
        }
        try {
            taskService.getTaskByID(id).setDescription(task.getDescription());
            taskService.getTaskByID(id).setTitle(task.getTitle());
            taskService.getTaskByID(id).setLocalDateTime(task.getLocalDateTime());
            model.addAttribute("task", taskService.getTaskByID(id));
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        model.addAttribute("subTask",new SubTask());
        return "taskDetail";
    }

    @PostMapping("/{id}/sub/create")
    public String addSubTask(@ModelAttribute @Valid SubTask subTask, BindingResult bindingResult, Model model, @PathVariable("id") Integer id){
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("task", taskService.getTaskByID(id));
                model.addAttribute("subTask",subTask);
                return "taskDetail";
            }
            taskService.getTaskByID(id).addSubTask(subTask);
            model.addAttribute("task", taskService.getTaskByID(id));
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "taskDetail";
    }

    @GetMapping("/{idT}/sub/remove/{idS}")
    public String removeSubTask(Model model, @PathVariable("idT") Integer idT,@PathVariable("idS") Integer idS){
        try {
            taskService.getTaskByID(idT).removeSubTaskWithID(idS);
            model.addAttribute("task", taskService.getTaskByID(idT));
            model.addAttribute("subTask",new SubTask());
        } catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
        return "taskDetail";
    }
}
