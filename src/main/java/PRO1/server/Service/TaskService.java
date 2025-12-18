package PRO1.server.Service;

import PRO1.server.DTO.TaskRequest;
import PRO1.server.DTO.TaskResponse;
import PRO1.server.Mapper.TaskMapper;
import PRO1.server.Model.Task;
import PRO1.server.Model.User;
import PRO1.server.Repository.TaskRepository;
import PRO1.server.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository usersRepository;

    public TaskService(TaskRepository taskRepository, UserRepository usersRepository) {
        this.taskRepository = taskRepository;
        this.usersRepository = usersRepository;
    }

    public TaskResponse create(TaskRequest request) {
        User user = usersRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Task task = TaskMapper.toEntity(request, user);
        taskRepository.save(task);

        return TaskMapper.toResponse(task);
    }

    public List<TaskResponse> getAllForUser(int userId) {
        User user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return taskRepository.findByUser(user)
                .stream()
                .map(TaskMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse toggleCompleted(int taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setCompleted(!task.isCompleted());

        return TaskMapper.toResponse(taskRepository.save(task));
    }

    public void delete(int taskId) {
        taskRepository.deleteById(taskId);
    }
}
