package com.example.myfinalproject.weather.model;

import lombok.Data;

/**
 * @author Oksana
 */
@Data
public class Weather {

   private Long id;
   private String main;
   private String description;
   private String icon;

   public String getIcon() {
      return icon;
   }

   public void setIcon(String icon) {
      this.icon = icon;
   }
}
