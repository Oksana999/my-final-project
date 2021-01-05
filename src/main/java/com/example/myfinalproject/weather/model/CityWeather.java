package com.example.myfinalproject.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oksana
 */
@Data
public class CityWeather {

   private Coordinate coord;

   private List<Weather> weather;

   private String base;

   private WeatherInfo main;

   private Integer visibility;

   private Wind wind;

   private Clouds clouds;

   @JsonProperty("dt")
   private Long dateTime;

   private Long id;

   private String name;

   private Long cod;

   private CityInfo sys;

   private Integer timezone;

   private Rain rain;

   private Snow snow;



      public List<Weather> getWeather() {
      return weather;
   }

   public void setWeather(List<Weather> weather) {
      this.weather = weather;
   }


}
