package com.example.myfinalproject.controllers;

import com.example.myfinalproject.data.CountryRepository;
import com.example.myfinalproject.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Controller
public abstract class ParentController {

   @Autowired
   private CountryRepository countryRepository;

   @ModelAttribute("countries")
   public List<Country> getCountries() {
      List<Country> countries = new ArrayList<>();
      Iterable<Country> all = countryRepository.findAll();
      all.forEach(countries::add);

      return countries;
   }
}
