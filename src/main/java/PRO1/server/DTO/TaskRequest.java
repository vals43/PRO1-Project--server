package PRO1.server.DTO;

import PRO1.server.Enum.Priority;
import java.time.LocalDate;

public record TaskRequest(
        String title,
        String description,
        Priority priority,
        LocalDate dueDate,
        int userId
) {
    @Override
    public String title() {
        return title;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public Priority priority() {
        return priority;
    }

    @Override
    public LocalDate dueDate() {
        return dueDate;
    }

    @Override
    public int userId() {
        return userId;
    }
}
