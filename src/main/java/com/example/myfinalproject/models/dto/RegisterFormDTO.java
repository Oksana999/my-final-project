package com.example.myfinalproject.models.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.Email;

/**
 * @author Oksana
 */
public class RegisterFormDTO extends LoginFormDTO{
   @NotNull
   @Email( message = "Invalid email")
   private String email;

//   public RegisterFormDTO() {
//   }


   private String verifyPassword;


   public String getVerifyPassword() {
      return verifyPassword;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public void setVerifyPassword(String verifyPassword) {
      this.verifyPassword = verifyPassword;
   }
}
