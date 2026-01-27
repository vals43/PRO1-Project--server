package PRO1.server.Model;

import PRO1.server.Enum.Frequency;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "habit")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habit_id", nullable = false, unique = true)
    private int id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "frequency", length = 20)
    private Frequency frequency;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HabitLog> logs;

    public Habit() {
    }

    public Habit(String name, Frequency frequency, User user) {
        this.name = name;
        this.frequency = frequency;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public int getHabitId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<HabitLog> getLogs() { return logs; }
    public void setLogs(List<HabitLog> logs) { this.logs = logs; }
    
    @Override
    public String toString() {
        return "Habit{" +
                "habitId=" + id +
                ", name='" + name + '\'' +
                ", frequency='" + frequency + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }

}
