package com.example.myfinalproject.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Oksana
 */
@Data
public class Rain {

   @JsonProperty("1h")
   private Double rain1H;

}
