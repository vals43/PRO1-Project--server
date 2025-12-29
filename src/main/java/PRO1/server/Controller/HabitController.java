package PRO1.server.Controller;

import PRO1.server.DTO.HabitRequest;
import PRO1.server.DTO.HabitResponse;
import PRO1.server.Service.HabitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit")
@CrossOrigin(origins = "http://localhost:5173")
public class HabitController {

    private final HabitService service;

    public HabitController(HabitService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String HelloWorld(){
        return "Habit work ";
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<HabitResponse>> getAll(@PathVariable int userId) {
        return ResponseEntity.ok(service.getHabits(userId));
    }


    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getOne(@PathVariable int id) {
        return ResponseEntity.ok(service.getHabit(id));
    }


    @PostMapping("/create")
    public ResponseEntity<HabitResponse> create(@RequestBody HabitRequest request) {
        return ResponseEntity.ok(service.createHabit(request));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<HabitResponse> update(
            @PathVariable int id,
            @RequestBody HabitRequest request) {

        return ResponseEntity.ok(service.updateHabit(id, request));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.deleteHabit(id);
        return ResponseEntity.ok("Habit deleted");
    }
}
