package com.example.myfinalproject.models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Oksana
 */
@Entity
public class User extends AbstractEntity {

   private static final  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


   @NotBlank(message = "User userName name is required")
   private String username;

   @NotBlank(message = "Email is required")
   @Email(message = "Invalid email. Try again.")
   private String email;

   @NotBlank(message = "Password is required")
   private String pwHash;

   @Column(name = "user_role")
   private Role role;

   @OneToOne(mappedBy = "user", cascade = CascadeType.ALL,
           fetch = FetchType.LAZY, optional = false)
   private Account account;

   public User() {
   }


   public User(String username, String email, String password) {
      this.username = username;
      this.email = email;
      this.pwHash = encoder.encode(password);
      this.role = Role.ADMIN;
   }


   public boolean isMatchingPassword(String password){
      return encoder.matches(password, pwHash);
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return pwHash;
   }

   public void setPassword(String password) {
      this.pwHash = password;
   }

   public void setRole(Role role) {
      this.role = role;
   }

   public Role getRole() {
      return role;
   }

   public Account getAccount() {
      return account;
   }

   public void setAccount(Account account) {
      this.account = account;
   }

   @Override
   public String toString() {
      return "User{" +
              "username='" + username + '\'' +
              ", email='" + email + '\'' +
              ", pwHash='" + pwHash + '\'' +
              ", role=" + role +
              ", account=" + account +
              '}';
   }
}
