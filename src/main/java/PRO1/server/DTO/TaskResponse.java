package PRO1.server.DTO;

import PRO1.server.Enum.Priority;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TaskResponse(
        int taskId,
        String title,
        String description,
        Priority priority,
        boolean completed,
        LocalDate dueDate,
        LocalDateTime createdAt,
        int userId
) {}
