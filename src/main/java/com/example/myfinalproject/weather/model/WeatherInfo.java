package com.example.myfinalproject.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Oksana
 */
@Data
public class WeatherInfo {

   private Double temp;
   private Integer pressure;
   private Integer humidity;

   @JsonProperty("feels_like")
   private Double feelsLike;
   @JsonProperty("temp_min")
   private Double tempMin;
   @JsonProperty("temp_max")
   private Double tempMax;
   @JsonProperty("sea_level")
   private Double seaLevel;
   @JsonProperty("grnd_level")
   private Integer grndLevel;

   public Double getTemp() {
      return temp;
   }

   public void setTemp(Double temp) {
      this.temp = temp;
   }
}
