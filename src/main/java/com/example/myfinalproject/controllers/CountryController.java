package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.CountryRepository;
import com.example.myfinalproject.data.HotelRepository;
import com.example.myfinalproject.data.UserRepository;
import com.example.myfinalproject.models.Country;
import com.example.myfinalproject.models.Hotel;
import com.example.myfinalproject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("countries")
public class CountryController extends ParentController {

@Autowired
private CountryRepository countryRepository;
@Autowired
private UserRepository userRepository;


//   @GetMapping()
//   public String getCountries(@RequestParam(required = false) Integer countryId, Model model){
//
//      if(countryId == null) {
//      model.addAttribute("title", "Countries");
//      model.addAttribute("countries", countryRepository.findAll());
//      } else {
//         Optional<Country> result = countryRepository.findById(countryId);
//         if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Country ID: " + countryId);
//         } else {
//            Country country = result.get();
//            model.addAttribute("title", "Country: " + country.getName());
//            model.addAttribute("country", country);
//         }
//      }
//
//      return "countries/index";
//   }

   @GetMapping
   public String getCountries(Model model){
     // List<Country> iterCountries = (List<Country>) countryRepository.findAll();
      List<Country> countries = new ArrayList<>();
      Iterable<Country> all = countryRepository.findAll();
      all.forEach(countries::add);

      model.addAttribute("title", "Countriecs");
      model.addAttribute("countries", countries);

      return "countries/index";

   }
   @GetMapping("create")
   public String createHotel(Model model){

      model.addAttribute("title", "Create Country");
      model.addAttribute("country", new Country());

      return "countries/create";
   }

   @PostMapping("create")
   public String processCreateCountryForm(@ModelAttribute @Valid Country newCountry,
                                        Errors errors, Model model) {
      if(errors.hasErrors()) {
         model.addAttribute("title", "Create Country");
         return "countries/create";
      }
      countryRepository.save(newCountry);

         return "redirect:";

      }



   @GetMapping("display")
   public String displayCountry(@RequestParam Integer countryId, Model model) {

      Optional<Country> result = countryRepository.findById(countryId);

      if (result.isEmpty()) {
         model.addAttribute("title", "Invalid Country ID: " + countryId);
      } else {
         model.addAttribute("countries", countryRepository.findAll());
         Country country = result.get();
         model.addAttribute("title", country.getName());
         model.addAttribute("country", country);
         model.addAttribute("hotels", country.getHotels());

      }

      return "countries/display";
   }

   @GetMapping(value = "users/admin")
   public String admin(Model model, HttpSession session){

      Object userObj = session.getAttribute("user");
      System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!");
      System.out.println(userObj.getClass());
      System.out.println(userObj.toString());
      User currentUser = (User)userObj;

      if( currentUser.getRole().toString().equals("ADMIN")) {
         List<User> users = new ArrayList<>();
         Iterable<User> usersIter = userRepository.findAll();
         usersIter.forEach(users::add);

         model.addAttribute("users", users);

         return "users/admin";
      }else {
         model.addAttribute("message", "You do not have permission to admin role ");

         return "error";
      }
   }
   @GetMapping("index")
   public String getHome(Model model){

      model.addAttribute("title", "Welcome to website !");

      List<Country> countries = new ArrayList<>();
      Iterable<Country> all = countryRepository.findAll();
      all.forEach(countries::add);

      model.addAttribute("countries", countries);

      return "index";
   }

}
