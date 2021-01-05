package com.example.myfinalproject.models.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Oksana
 */
public class LoginFormDTO {
   @NotNull
   @Size(min = 3, max = 20, message = "Invalid username. Must be between 3 and 20 characters")
   private String username;

   @NotNull
   @Size(min = 3, max = 20, message = "Invalid password. Must be between 3 and 20 characters")
   private String password;


   public LoginFormDTO() {}

//   public LoginFormDTO() {
//   }


   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }
}
