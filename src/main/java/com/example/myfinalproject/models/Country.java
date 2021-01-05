package com.example.myfinalproject.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Oksana
 */
@Entity
public class Country extends AbstractEntity{

   @NotNull(message = "Name is required")
   private String name;

   @OneToMany(mappedBy = "country",  cascade = CascadeType.ALL,
           orphanRemoval = true)
   private List<Hotel> hotels = new ArrayList<>();

   public Country() {
   }

   public Country(String name){
      this.name = name;

   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public List<Hotel> getHotels() {
      return hotels;
   }

   public void setHotels(List<Hotel> hotels) {
      this.hotels = hotels;
   }

//   @Override
//   public String toString() {
//      return "Country{" +
//              "name='" + name + '\'' +
//              ", hotels=" + hotels +
//              '}';
//   }
}
