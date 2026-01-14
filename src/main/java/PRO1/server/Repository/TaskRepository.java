package PRO1.server.Repository;

import PRO1.server.Model.Task;
import PRO1.server.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByUser(User user);

}
