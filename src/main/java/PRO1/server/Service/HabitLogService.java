package PRO1.server.Service;

import PRO1.server.DTO.HabitLogRequest;
import PRO1.server.DTO.HabitLogResponse;
import PRO1.server.Mapper.HabitLogMapper;
import PRO1.server.Model.HabitLog;
import PRO1.server.Model.Habit;
import PRO1.server.Repository.HabitLogRepository;
import PRO1.server.Repository.HabitsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HabitLogService {

    private final HabitLogRepository logRepo;
    private final HabitsRepository habitRepo;

    public HabitLogService(HabitLogRepository logRepo, HabitsRepository habitRepo) {
        this.logRepo = logRepo;
        this.habitRepo = habitRepo;
    }

    public HabitLogResponse save(HabitLogRequest request) {
        Habit habit = habitRepo.findById(request.habitId())
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        // éviter doublon même jour
        HabitLog log = logRepo.findByHabitAndLoggedAt(habit, LocalDate.now())
                .orElseGet(() -> HabitLogMapper.toEntity(request, habit));

        log.setStatus(request.status());
        return HabitLogMapper.toDTO(logRepo.save(log));
    }

    public List<HabitLogResponse> getLogs(Long habitId) {
        Habit habit = habitRepo.findById(habitId)
                .orElseThrow(() -> new IllegalArgumentException("Habit not found"));

        return logRepo.findByHabit(habit).stream()
                .map(HabitLogMapper::toDTO)
                .toList();
    }
}
