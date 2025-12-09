package PRO1.server.Repository;

import PRO1.server.Model.HabitLog;
import PRO1.server.Model.Habit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

    List<HabitLog> findByHabit(Habit habit);

    Optional<HabitLog> findByHabitAndLoggedAt(Habit habit, LocalDate loggedAt);

}
