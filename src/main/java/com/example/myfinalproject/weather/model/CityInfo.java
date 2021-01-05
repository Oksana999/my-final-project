package com.example.myfinalproject.weather.model;

import lombok.Data;

/**
 * @author Oksana
 */
@Data
public class CityInfo {

   private Integer type;
   private Long id;
   private Double message;
   private String country;
   private Long sunrise;
   private Long sunset;
}
