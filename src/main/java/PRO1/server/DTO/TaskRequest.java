package PRO1.server.DTO;

import PRO1.server.Enum.Priority;
import java.time.LocalDate;

public record TaskRequest(
        String title,
        String description,
        Priority priority,
        LocalDate dueDate,
        int userId
) {}
