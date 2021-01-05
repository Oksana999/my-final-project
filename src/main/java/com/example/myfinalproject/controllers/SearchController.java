package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.HotelRepository;
import com.example.myfinalproject.models.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Controller
@RequestMapping("/search")
public class SearchController  extends ParentController {

   @Autowired
   private HotelRepository hotelRepository;

   @GetMapping
   public String searchHotel(Model model){

      model.addAttribute("title", "Found items");


      return "search";
   }

   @PostMapping
   public String searchHotelByName(Model model, @RequestParam String searchItem){

      List<Hotel> hotels = new ArrayList<>();
      Iterable<Hotel> hotelIterable = hotelRepository.findAll();
      hotelIterable.forEach(hotels :: add);

      List<Hotel> foundHotels = new ArrayList<>();
      for (Hotel hotel :hotels) {
         if(hotel.getName().toLowerCase().contains(searchItem.toLowerCase())){
            foundHotels.add(hotel);
         }

      }

      model.addAttribute("hotels", foundHotels);

      return "search";
//      return "redirect:";


   }

}
