package PRO1.server.Repository;

import PRO1.server.Model.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    List<HabitLog> findByHabit_Id(int habit_id);
}
