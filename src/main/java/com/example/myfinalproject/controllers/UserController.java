package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.CountryRepository;
import com.example.myfinalproject.data.UserRepository;
import com.example.myfinalproject.models.Country;
import com.example.myfinalproject.models.Role;
import com.example.myfinalproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
public class UserController extends ParentController {

   @Autowired
   private UserRepository userRepository;
   @Autowired
   private CountryRepository countryRepository;


   public UserController(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @GetMapping("/users")
   public String getAllUsers(Model model) {
      List<User> users = new ArrayList<>();

      Iterable<User> usersIter = userRepository.findAll();


      usersIter.forEach(users::add);


      model.addAttribute("users", users);
      return "users/index";
   }


   @GetMapping("users/{id}")
   public String displayEventDetails(@RequestParam Integer userId, Model model) {

      Optional<User> result = userRepository.findById(userId);

      if (result.isEmpty()) {
         model.addAttribute("title", "Invalid User ID: " + userId);
      } else {
         User user = result.get();
         model.addAttribute("title", user.getUsername());
         model.addAttribute("user", user);

      }

      return "users/display";
   }


   @GetMapping(value = "users/admin")
   public String admin(Model model, HttpSession session) {

      Object userObj = session.getAttribute("user");
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(userObj.getClass());
      System.out.println(userObj.toString());
      User currentUser = (User) userObj;

      if (currentUser.getRole().toString().equals("ADMIN")) {
         List<User> users = new ArrayList<>();
         Iterable<User> usersIter = userRepository.findAll();
         usersIter.forEach(users::add);

         model.addAttribute("users", users);

         return "users/admin";
      } else {
         model.addAttribute("message", "You do not have permission to admin role ");

         return "error";
      }
   }

   @GetMapping(value = "users/addrole")
   public String addrole(@RequestParam Integer userId, Model model) {
      Optional<User> userOpt = userRepository.findById(userId);
      if (userOpt.isPresent()) {
         User user = userOpt.get();
         model.addAttribute("user", user);
         List<Role> roles = new ArrayList<>();
         roles.add(Role.ADMIN);
         roles.add(Role.USER);
         model.addAttribute("roles", roles);

      }
      return "users/addrole";
   }

   @PostMapping(value = "users/addrole")
   public String userAddRole(User user, Role role) {

      User admin = userRepository.findById(user.getId()).get();
      admin.setRole(role);
      User newAdmin = this.userRepository.save(admin);

      return "users/display";
   }

   @GetMapping("users/index")
   public String getHome(Model model) {

      model.addAttribute("title", "Welcome to website !");

      List<Country> countries = new ArrayList<>();
      Iterable<Country> all = countryRepository.findAll();
      all.forEach(countries::add);

      model.addAttribute("countries", countries);

      return "index";
   }

}

