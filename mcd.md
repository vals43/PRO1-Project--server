Voici la **version entièrement traduite en anglais** des tables + colonnes, tout en gardant la même structure.

---

# 🛠️ **PostgreSQL Schema (English version)**

```sql
-- ==========================
-- TABLE User
-- ==========================
CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    avatar_url TEXT
);

-- ==========================
-- TABLE Habit
-- ==========================
CREATE TABLE habit (
    habit_id SERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    frequency VARCHAR(20) CHECK (frequency IN ('daily', 'weekly', 'monthly')),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);

-- ==========================
-- TABLE HabitLog (daily check)
-- ==========================
CREATE TABLE habit_log (
    log_id SERIAL PRIMARY KEY,
    log_date DATE NOT NULL,
    completed BOOLEAN DEFAULT FALSE,
    habit_id INT NOT NULL,
    FOREIGN KEY (habit_id) REFERENCES habit(habit_id) ON DELETE CASCADE
);

-- ==========================
-- TABLE Task
-- ==========================
CREATE TABLE task (
    task_id SERIAL PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    priority VARCHAR(20) CHECK (priority IN ('low', 'medium', 'high')),
    completed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    due_date DATE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id) ON DELETE CASCADE
);
```

---

# 🌱 **Inserts (translated)**

### 👤 **Users**

```sql
INSERT INTO users (email, password, avatar_url) VALUES
('alice@example.com', 'hashedpassword1', NULL),
('bob@example.com', 'hashedpassword2', 'https://cdn.site/avatar_bob.png');
```

---

### 📅 **Habits**

```sql
INSERT INTO habit (name, frequency, user_id) VALUES
('Workout', 'daily', 1),
('Read a book', 'weekly', 1),
('Call family', 'monthly', 2);
```

---

### 🗓️ **Habit Logs**

```sql
INSERT INTO habit_log (log_date, completed, habit_id) VALUES
('2025-02-01', TRUE, 1),
('2025-02-02', TRUE, 1),
('2025-02-03', FALSE, 1),

('2025-02-01', TRUE, 2),
('2025-02-08', TRUE, 2),

('2025-02-01', FALSE, 3);
```

---

### ✅ **Tasks**

```sql
INSERT INTO task (title, description, priority, completed, due_date, user_id) VALUES
('Buy fruits', 'Go to the market to buy apples and bananas', 'low', FALSE, '2025-02-10', 1),
('Finish the report', 'Report for Monday''s meeting', 'high', FALSE, '2025-02-05', 1),
('Pay internet', 'Monthly payment', 'medium', TRUE, '2025-02-01', 2);
```

---

Si tu veux, je peux aussi te générer :
📌 un fichier SQL téléchargeable
📌 des **indexes**, **views**, **triggers** (ex : auto-calcul de streaks)

Tu veux ajouter quoi ?
