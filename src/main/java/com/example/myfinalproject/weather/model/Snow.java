package com.example.myfinalproject.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Oksana
 */
@Data
public class Snow {

   @JsonProperty("snow.1h")
   private Integer snow1H;
}
