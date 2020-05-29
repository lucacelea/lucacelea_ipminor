package ucll.be.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ucll.be.todo.dto.TaskDTO;
import ucll.be.todo.service.TaskService;

@Controller
@RequestMapping("/")
public class IndexController {
    private final TaskService taskService;

    @Autowired
    public IndexController(TaskService taskService){this.taskService = taskService;}

    @GetMapping
    public String index(Model model){
        model.addAttribute("tasks", taskService.getTasks());
        model.addAttribute("taskDTO", new TaskDTO());
        return "tasks";
    }

}
