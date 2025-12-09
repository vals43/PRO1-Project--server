package PRO1.server.Model;

import PRO1.server.Enum.Frequency;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "habit")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "habit_id", nullable = false, unique = true)
    private int habitId;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "frequency", length = 20)
    private Frequency frequency;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "user_id")
    private Users user;

    public Habit() {
    }

    public Habit(String name, Frequency frequency, Users user) {
        this.name = name;
        this.frequency = frequency;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    public int getHabitId() {
        return habitId;
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

    public Users getUser() {
        return user;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Habit{" +
                "habitId=" + habitId +
                ", name='" + name + '\'' +
                ", frequency='" + frequency + '\'' +
                ", createdAt=" + createdAt +
                ", user=" + (user != null ? user.getUser_id() : null) +
                '}';
    }
}
