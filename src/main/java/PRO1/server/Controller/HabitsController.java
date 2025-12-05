package PRO1.server.Controller;

import PRO1.server.Model.Habits;
import PRO1.server.Model.Users;
import PRO1.server.Repository.HabitsRepository;
import PRO1.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitsController {

    @Autowired
    private HabitsRepository habitRepository;

    @Autowired
    private UserRepository userRepository;


    // -----------------------
    // GET ALL habits by user
    // -----------------------
    @GetMapping("/user/{user}")
    public ResponseEntity<List<Habits>> getHabitsByUser(@PathVariable Users user) {
        List<Habits> habits = habitRepository.findByUser(user);
        return ResponseEntity.ok(habits);
    }


    // -----------------------
    // GET habit by id
    // -----------------------
    @GetMapping("/{id}")
    public ResponseEntity<Habits> getHabit(@PathVariable int id) {
        Habits habit = habitRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));
        return ResponseEntity.ok(habit);
    }


    // -----------------------
    // CREATE habit
    // -----------------------
    @PostMapping("/create/{userId}")
    public ResponseEntity<Habits> createHabit(@PathVariable Long userId, @RequestBody Habits habit) {

        Users user = userRepository.findById(Math.toIntExact(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        habit.setUser(user);

        Habits saved = habitRepository.save(habit);
        return ResponseEntity.ok(saved);
    }


    // -----------------------
    // UPDATE habit
    // -----------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Habits> updateHabit(@PathVariable Long id, @RequestBody Habits habitDetails) {

        Habits habit = habitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habit not found"));

        habit.setName(habitDetails.getName());
        habit.setFrequency(habitDetails.getFrequency());

        Habits updated = habitRepository.save(habit);

        return ResponseEntity.ok(updated);
    }


    // -----------------------
    // DELETE habit
    // -----------------------
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteHabit(@PathVariable Long id) {

        if (!habitRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Habit not found");
        }

        habitRepository.deleteById(id);

        return ResponseEntity.ok("Habit deleted");
    }
}
