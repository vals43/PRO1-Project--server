package PRO1.server.Mapper;

import PRO1.server.DTO.HabitRequest;
import PRO1.server.DTO.HabitResponse;
import PRO1.server.Model.Habit;
import PRO1.server.Model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HabitMapper {

    public Habit toEntity(HabitRequest req, User user) {
        return new Habit(req.name(), req.frequency(), user);
    }

    public HabitResponse toDTO(Habit habit) {
        return new HabitResponse(
                habit.getHabitId(),
                habit.getName(),
                habit.getFrequency(),
                habit.getCreatedAt(),
                habit.getUser().getId(),
                habit.getLogs() == null ? List.of() : habit.getLogs()
                        .stream()
                        .map(HabitLogMapper::toDTO)
                        .collect(Collectors.toList())
        );
    }
}
