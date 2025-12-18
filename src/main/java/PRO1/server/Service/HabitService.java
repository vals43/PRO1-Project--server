package PRO1.server.Service;

import PRO1.server.DTO.HabitRequest;
import PRO1.server.DTO.HabitResponse;
import PRO1.server.Mapper.HabitMapper;
import PRO1.server.Model.Habit;
import PRO1.server.Model.User;
import PRO1.server.Repository.HabitsRepository;
import PRO1.server.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitService {

    private final HabitMapper HabitMapper;
    private final HabitsRepository habitsRepository;
    private final UserRepository userRepository;

    public HabitService(HabitMapper habitMapper, HabitsRepository habitsRepository, UserRepository userRepository) {
        HabitMapper = habitMapper;
        this.habitsRepository = habitsRepository;
        this.userRepository = userRepository;
    }


    public List<HabitResponse> getHabits(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return habitsRepository.findByUser(user)
                .stream()
                .map(HabitMapper::toDTO)
                .collect(Collectors.toList());
    }


    public HabitResponse getHabit(int id) {
        Habit habit = habitsRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        return HabitMapper.toDTO(habit);
    }


    public HabitResponse createHabit(HabitRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Habit habit = HabitMapper.toEntity(request, user);
        habitsRepository.save(habit);

        return HabitMapper.toDTO(habit);
    }


    public HabitResponse updateHabit(int id, HabitRequest request) {
        Habit habit = habitsRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        habit.setName(request.name());
        habit.setFrequency(request.frequency());

        habitsRepository.save(habit);

        return HabitMapper.toDTO(habit);
    }


    public void deleteHabit(int id) {
        if (!habitsRepository.existsById((long) id)) {
            throw new RuntimeException("Habit not found");
        }
        habitsRepository.deleteById((long) id);
    }
}
