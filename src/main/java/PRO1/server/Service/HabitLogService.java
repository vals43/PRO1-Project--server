package PRO1.server.Service;

import PRO1.server.DTO.HabitLogRequest;
import PRO1.server.DTO.HabitLogResponse;
import PRO1.server.Model.HabitLog;
import PRO1.server.Model.Habit;
import PRO1.server.Repository.HabitLogRepository;
import PRO1.server.Repository.HabitsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final HabitsRepository habitRepository;

    public HabitLogService(HabitLogRepository habitLogRepository, HabitsRepository habitRepository) {
        this.habitLogRepository = habitLogRepository;
        this.habitRepository = habitRepository;
    }

    public HabitLogResponse createHabitLog(HabitLogRequest request) {
        Habit habit = habitRepository.findById(request.getHabitId())
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        HabitLog habitLog = new HabitLog(habit, request.isCompleted());
        HabitLog saved = habitLogRepository.save(habitLog);

        return new HabitLogResponse(
                saved.getLogId(),
                (long) saved.getHabit().getHabitId(),
                saved.isCompleted(),
                saved.getLogDate()
        );
    }

    public List<HabitLogResponse> getLogsByHabitId(Long habitId) {
        return habitLogRepository.findByHabit_Id(Math.toIntExact(habitId))
                .stream()
                .map(log -> new HabitLogResponse(
                        log.getLogId(),
                        (long) log.getHabit().getHabitId(),
                        log.isCompleted(),
                        log.getLogDate()
                ))
                .collect(Collectors.toList());
    }
}
