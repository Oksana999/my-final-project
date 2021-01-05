package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.AccountRepository;
import com.example.myfinalproject.data.TransactionRepository;
import com.example.myfinalproject.data.UserRepository;
import com.example.myfinalproject.models.Account;
import com.example.myfinalproject.models.User;
import com.example.myfinalproject.models.dto.LoginFormDTO;
import com.example.myfinalproject.models.dto.RegisterFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;

/**
 * @author Oksana
 */
@Controller
public class AuthenticationController extends ParentController {

   private UserRepository userRepository;

   private AccountRepository accountRepository;

   private TransactionRepository transactionRepository;

   public AuthenticationController(UserRepository userRepository, AccountRepository accountRepository,
                                   TransactionRepository transactionRepository) {
      this.userRepository = userRepository;
      this.accountRepository = accountRepository;
      this.transactionRepository = transactionRepository;
   }

   private static final String userSessionKey = "user";

   public User getUserFromSession(HttpSession session) {
      User user = (User) session.getAttribute(userSessionKey);

//      if(user == null){
//         return null;
//      }
//
//      Optional<User> user = userRepository.findById(userId);
//
//      if(user.isEmpty()){
//         return null;
//      }
      return user;
   }

   private void setUserInSession(HttpSession session, User user) {
//      session.setAttribute(userSessionKey, user.getId());
      session.setAttribute(userSessionKey, user);
   }

   @GetMapping("/register")
   public String displayRegistrationForm(Model model) {
      model.addAttribute("registerFormDTO", new RegisterFormDTO());
      model.addAttribute("title", "Register");
      return "register";
   }

   @PostMapping("/register")
   public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                         Errors errors, HttpServletRequest request,
                                         Model model) {

      if (errors.hasErrors()) {
         model.addAttribute("title", "Register");
         return "register";
      }

      User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

      if (existingUser != null) {
         errors.rejectValue("username", "username already exists", "A user with that username already exists");
         model.addAttribute("title", "Register");
         return "register";
      }

      String password = registerFormDTO.getPassword();

      String verifyPassword = registerFormDTO.getVerifyPassword();
      if (!password.equals(verifyPassword)) {
         errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
         model.addAttribute("title", "Register");
         return "register";
      }

      User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getEmail(), registerFormDTO.getPassword());
      User user = userRepository.save(newUser);

      Account account = new Account();
      account.setUser(user);
      account.setAmount(BigDecimal.ZERO);
      accountRepository.save(account);


      setUserInSession(request.getSession(), newUser);

      return "redirect:/index";
   }

   @GetMapping("/login")
   public String displayLoginForm(Model model) {
      model.addAttribute(new LoginFormDTO());
      model.addAttribute("title", "Log In");

      return "login";
   }

   @PostMapping("/login")
   public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                  Errors errors, HttpServletRequest request,
                                  Model model) {

      if (errors.hasErrors()) {
         model.addAttribute("title", "Log In");
         return "login";
      }

      User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

      if (theUser == null) {
         errors.rejectValue("username", "user.invalid", "The given credentials are wrong");
         model.addAttribute("title", "Log In");
         return "login";
      }

      String password = loginFormDTO.getPassword();

      if (!theUser.isMatchingPassword(password)) {
         errors.rejectValue("password", "password.invalid", "The given credentials are wrong");
         model.addAttribute("title", "Log In");
         return "login";
      }

      setUserInSession(request.getSession(), theUser);

      return "redirect:/index";
   }

   @GetMapping("/logout")
   public String logout(HttpServletRequest request) {
      request.getSession().invalidate();
      return "redirect:/login";
   }
}
