package com.example.myfinalproject.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author Oksana
 */
@Entity
public class Hotel extends AbstractEntity {

   @NotNull(message = "Hotel name is required")
   private String name;

   private String photo;

   @NotNull(message = "Description is required")
   private String description;

   @NotNull(message = "City is required")
   private String city;

   @NotNull(message = "Price is required")
   private BigDecimal price;

   @ManyToOne(fetch = FetchType.LAZY)
   @NotNull(message = "Country is required")
   private Country country;

   public Hotel() {
   }

   public Hotel(String name, String description, String photo, Country country, BigDecimal price, String city) {

      this.name = name;
      this.description = description;
      this.country = country;
      this.photo = photo;
      this.price = price;
      this.city = city;
   }


   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getPhoto() {
      return photo;
   }

   public void setPhoto(String photo) {
      this.photo = photo;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Country getCountry() {
      return country;
   }

   public void setCountry(Country country) {
      this.country = country;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }

   public String getCity() {
      return city;
   }

   public void setCity(String city) {
      this.city = city;
   }

   //   @Override
//   public String toString() {
//      return "Hotel{" +
//              "name='" + name + '\'' +
//              ", photo='" + photo + '\'' +
//              ", description='" + description + '\'' +
//              ", country=" + country +
//              '}';
//   }
}
