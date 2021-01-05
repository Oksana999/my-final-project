package com.example.myfinalproject;

import com.example.myfinalproject.weather.WeatherApiClient;
import com.example.myfinalproject.weather.model.CityWeather;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class MyFinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyFinalProjectApplication.class, args);
	}

	@Bean
	public WeatherApiClient weatherApi() {
		return new WeatherApiClient();
	}

	@Bean
	public CityWeather cityWeather(){
		return  new CityWeather();
	}

}
