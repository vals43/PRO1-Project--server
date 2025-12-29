package PRO1.server.Controller;

import PRO1.server.DTO.HabitLogRequest;
import PRO1.server.DTO.HabitLogResponse;
import PRO1.server.Service.HabitLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit/log")
@CrossOrigin(origins = "http://localhost:5173")
public class HabitLogController {

    private final HabitLogService habitLogService;

    public HabitLogController(HabitLogService habitLogService) {
        this.habitLogService = habitLogService;
    }

    @PostMapping
    public HabitLogResponse createLog(@RequestBody HabitLogRequest request) {
        return habitLogService.createHabitLog(request);
    }

    @GetMapping("/{habitId}")
    public List<HabitLogResponse> getLogs(@PathVariable Long habitId) {
        return habitLogService.getLogsByHabitId(habitId);
    }
}
