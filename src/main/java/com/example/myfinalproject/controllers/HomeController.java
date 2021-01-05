package com.example.myfinalproject.controllers;

import com.example.myfinalproject.models.Country;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Controller
public class HomeController extends ParentController {

   @GetMapping("index")
   public String getHome(Model model){

      model.addAttribute("title", "Welcome to website !");

      return "index";
   }

   @GetMapping("counrties/carousel")
   public String getCarousel(Model model){
      return "/counrties/carousel";
   }

   @GetMapping("fragments")
   public String getFragments(Model model){

      List<Country> countryList = new ArrayList<>();
      model.addAttribute("countryList", countryList);

      return "/fragments";
   }

   @GetMapping
   public String getIndex(Model model){

      List<Country> countryList = new ArrayList<>();
      model.addAttribute("countryList", countryList);
      model.addAttribute("title", "Welcome to website !");

      return "index";
   }

}
