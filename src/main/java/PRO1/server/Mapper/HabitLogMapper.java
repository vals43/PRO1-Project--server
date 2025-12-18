package PRO1.server.Mapper;

import PRO1.server.DTO.HabitLogRequest;
import PRO1.server.DTO.HabitLogResponse;
import PRO1.server.Model.Habit;
import PRO1.server.Model.HabitLog;

public class HabitLogMapper {

    public static HabitLog toEntity(HabitLogRequest req, Habit habit) {
        HabitLog log = new HabitLog();
        log.setHabit(habit);
        return log;
    }

    public static HabitLogResponse toDTO(HabitLog log) {
        return new HabitLogResponse(
                log.getLogId(),
                (long) log.getHabit().getHabitId(),
                log.isCompleted(),
                log.getLogDate()
        );
    }
}
