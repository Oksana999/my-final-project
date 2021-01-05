package com.example.myfinalproject.controllers;

import com.example.myfinalproject.weather.WeatherApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Oksana
 */
@RestController
public class TestWeatherController {

   private final WeatherApiClient api;

   @Autowired
   public TestWeatherController(WeatherApiClient api) {
      this.api = api;
   }

   @GetMapping("/weather")
   public String getCityWeather(@RequestParam(value = "city") String city) {
      String cityWeather = api.getHtmlCityWeather(city);

      //model.addAttribute("weather", cityWeather);

      return cityWeather;
   }
}
