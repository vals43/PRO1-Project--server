package PRO1.server.Model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id") // On garde le lien avec ta colonne SQL actuelle
   private Integer id; // Renommé de user_id à id

   @Column(nullable = false)
   private String name;

   @Column(name = "last_name")
   private String last_name;

   @Column(nullable = false, unique = true)
   private String email;

   private String password;

   public User() {}

   // Getters et Setters mis à jour
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() { return name; }
   public void setName(String name) { this.name = name; }

   public String getLast_name() { return last_name; }
   public void setLast_name(String last_name) { this.last_name = last_name; }

   public String getEmail() { return email; }
   public void setEmail(String email) { this.email = email; }

   public String getPassword() { return password; }
   public void setPassword(String password) { this.password = password; }
}