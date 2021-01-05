package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.CountryRepository;
import com.example.myfinalproject.data.HotelRepository;
import com.example.myfinalproject.data.UserRepository;
import com.example.myfinalproject.models.Country;
import com.example.myfinalproject.models.Hotel;
import com.example.myfinalproject.models.User;
import com.example.myfinalproject.weather.CityWeatherException;
import com.example.myfinalproject.weather.WeatherApiClient;
import com.example.myfinalproject.weather.model.CityWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("hotels")
public class HotelController extends ParentController {

   @Autowired
   private HotelRepository hotelRepository;
   @Autowired
   private CountryRepository countryRepository;
   @Autowired
   private UserRepository userRepository;

   @Autowired
   private WeatherApiClient weatherApiClient;

//   @GetMapping()
//   public String getHotels(@RequestParam(required = false) Integer hotelId, Model model){
//
//      if(hotelId == null) {
//         model.addAttribute("title", "Hotels");
//         model.addAttribute("hotels", hotelRepository.findAll());
//
//      } else {
//         Optional<Hotel> result = hotelRepository.findById(hotelId);
//         if (result.isEmpty()) {
//            model.addAttribute("title", "Invalid Hotel ID: " + hotelId);
//         } else {
//            Hotel hotel = result.get();
//            model.addAttribute("title", "Hotel: " + hotel.getName());
//            model.addAttribute("hotel", hotel);
//         }
//      }
//
//         return "hotels/index";
//      }

   @GetMapping
   public String getHotels(Model model) {

      model.addAttribute("title", "Hotels");
      model.addAttribute("hotels", hotelRepository.findAll());

      return "hotels/index";
   }

   @GetMapping("create")
   public String createHotel(Model model) {

      model.addAttribute("title", "Create Hotel");
      model.addAttribute("hotel", new Hotel());
      model.addAttribute("countries", countryRepository.findAll());

      return "hotels/create";
   }

   @PostMapping("create")
   public String processCreateHotelForm(@ModelAttribute @Valid Hotel newHotel,
                                        Errors errors, Model model, RedirectAttributes redirectAttrs) {
      if (errors.hasErrors()) {
         model.addAttribute("title", "Create Hotel");
         return "hotels/create";
      }

      System.out.println(newHotel.toString());
      Country country = newHotel.getCountry();
      country.getHotels().add(newHotel);


      hotelRepository.save(newHotel);
      redirectAttrs.addAttribute("hotelId", newHotel.getId());

      return "redirect:";
   }

   @GetMapping("/weather")
   public String getCityWeather(Model model, @RequestParam(value = "city") String city) {
      String cityWeather = weatherApiClient.getHtmlCityWeather(city);

      model.addAttribute("weather", cityWeather);

      return "/hotels/display :: #weather";
   }

   @GetMapping("display")
   public String displayHotel(@RequestParam Integer hotelId, Model model) {

      Optional<Hotel> result = hotelRepository.findById(hotelId);

      if (result.isEmpty()) {
         model.addAttribute("title", "Invalid Hotel ID: " + hotelId);
      } else {
         Hotel hotel = result.get();
         try {
            CityWeather cityWeather = weatherApiClient.getCityWeather(hotel.getCity());

            Double temp = cityWeather.getMain().getTemp();
            String icon = cityWeather.getWeather().get(0).getIcon();
            model.addAttribute("temp", temp);
         } catch (CityWeatherException e) {
            e.printStackTrace();
         }

         model.addAttribute("title", hotel.getName() + " Details");
         model.addAttribute("hotel", hotel);

         model.addAttribute("city", hotel.getCity());
      }

      return "hotels/display";
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

   @GetMapping("index")
   public String getHome(Model model) {

      model.addAttribute("title", "Welcome to website !");

      List<Country> countries = new ArrayList<>();
      Iterable<Country> all = countryRepository.findAll();
      all.forEach(countries::add);

      model.addAttribute("countries", countries);

      return "index";
   }

}
