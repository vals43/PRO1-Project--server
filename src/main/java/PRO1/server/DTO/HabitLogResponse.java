package PRO1.server.DTO;

import java.time.LocalDate;

public record HabitLogResponse(Long logId, Long habitId, boolean status, LocalDate loggedAt) {}
