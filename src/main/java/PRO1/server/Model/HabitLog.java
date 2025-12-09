package PRO1.server.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "habit_log")
public class HabitLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(nullable = false)
    private boolean status;

    @Column(name = "logged_at", nullable = false)
    private LocalDate loggedAt = LocalDate.now();

    public HabitLog() {}

    public HabitLog(Habit habit, boolean status) {
        this.habit = habit;
        this.status = status;
        this.loggedAt = LocalDate.now();
    }

    public Long getLogId() { return logId; }
    public Habit getHabit() { return habit; }
    public boolean isStatus() { return status; }
    public LocalDate getLoggedAt() { return loggedAt; }

    public void setStatus(boolean status) { this.status = status; }
}
