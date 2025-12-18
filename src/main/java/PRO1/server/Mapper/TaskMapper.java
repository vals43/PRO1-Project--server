package PRO1.server.Mapper;

import PRO1.server.DTO.TaskRequest;
import PRO1.server.DTO.TaskResponse;
import PRO1.server.Model.Task;
import PRO1.server.Model.User;

public class TaskMapper {

    public static Task toEntity(TaskRequest req, User user) {
        Task task = new Task(
                req.title(),
                req.description(),
                req.priority(),
                req.dueDate(),
                user
        );
        return task;
    }

    public static TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getTaskId(),
                task.getTitle(),
                task.getDescription(),
                task.getPriority(),
                task.isCompleted(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUser().getUser_id()
        );
    }
}
