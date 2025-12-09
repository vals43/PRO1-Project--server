package PRO1.server.Controller;

import PRO1.server.DTO.HabitLogRequest;
import PRO1.server.DTO.HabitLogResponse;
import PRO1.server.Service.HabitLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit/log")
public class HabitLogController {

    private final HabitLogService service;

    public HabitLogController(HabitLogService service) {
        this.service = service;
    }

    @PostMapping
    public HabitLogResponse logHabit(@RequestBody HabitLogRequest request) {
        return service.save(request);
    }

    @GetMapping("/{habitId}")
    public List<HabitLogResponse> getLogs(@PathVariable Long habitId) {
        return service.getLogs(habitId);
    }
}
