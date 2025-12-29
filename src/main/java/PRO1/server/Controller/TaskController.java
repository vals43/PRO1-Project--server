package PRO1.server.Controller;

import PRO1.server.DTO.TaskRequest;
import PRO1.server.DTO.TaskResponse;
import PRO1.server.Service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
public class  TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }
    @GetMapping("/")
    public String HelloWorld(){
        return "Task Work ";
    }
    @PostMapping("/create")
    public TaskResponse create(@RequestBody TaskRequest request) {
        return service.create(request);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getAll(@PathVariable int userId) {
        return service.getAllForUser(userId);
    }

    @PatchMapping("/{taskId}/toggle")
    public TaskResponse toggle(@PathVariable int taskId) {
        return service.toggleCompleted(taskId);
    }
    @PutMapping("/{taskId}")
    public TaskResponse update(@PathVariable int taskId, @RequestBody TaskRequest request) {
        return service.update(taskId, request);
    }
    @DeleteMapping("/{taskId}")
    public String delete(@PathVariable int taskId) {
        service.delete(taskId);
        return "Task deleted successfully.";
    }
}
