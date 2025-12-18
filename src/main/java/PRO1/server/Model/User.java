package PRO1.server.Model;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "Users")
public class User {
   @Id
   @Column(name = "user_id" , nullable = false , unique = true , length = 20)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int user_id  ;

   @Column(name = "name" , nullable = false )
   private String name;

   @Column(name = "last_name" , nullable = true )
   private String last_name;

   @Column(name = "email" , nullable = false , unique = true )
   private String email ;

   @Column(name = "password")
   private String password ;

   public User() {

   }

   public User(String name, String last_name, String email, String password) {
      this.name = name;
      this.last_name = last_name;
      this.email = email;
      this.password = password;
   }

   public int getUser_id() {
      return user_id;
   }

   public String getName() {
      return name;
   }

   public String getEmail() {
      return email;
   }

   public String getLast_name(String lastName) {
      return last_name;
   }

   public String getPassword() {
      return password;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setLast_name(String last_name) {
      this.last_name = last_name;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String toString() {
      return "Users{" +
              "user_id=" + user_id +
              ", name='" + name + '\'' +
              ", last_name='" + last_name + '\'' +
              ", email='" + email + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return user_id == user.user_id && Objects.equals(name, user.name) && Objects.equals(last_name, user.last_name) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
   }

   @Override
   public int hashCode() {
      return Objects.hash(user_id, name, last_name, email, password);
   }
}
