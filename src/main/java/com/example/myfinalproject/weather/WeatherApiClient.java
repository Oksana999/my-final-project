package com.example.myfinalproject.weather;

import com.example.myfinalproject.weather.model.CityWeather;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * @author Oksana
 */
public class WeatherApiClient {

   //  api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}
   private final CloseableHttpClient httpClient;

   private final static String WEATHER_PATH_NAME = "/weather";

   private final static String API_KEY_QUERY = "appid";

   private final static String Q_QUERY = "q";

   private final static String API_KEY = "WEATHER_API_KEY";

   private final static String HTTP_URL = "https://api.openweathermap.org/data/2.5";


   public WeatherApiClient() {
      httpClient = HttpClients.createDefault();
   }

   public CityWeather getCityWeather(String city) throws CityWeatherException {
      String url = urlBuilder(WEATHER_PATH_NAME)
              .queryParam(Q_QUERY, city)
              .build()
              .toUriString();

      HttpGet request = new HttpGet(url);
      try {
         CloseableHttpResponse response = httpClient.execute(request);


         HttpEntity entity = response.getEntity();
         if (entity != null) {

            String result = EntityUtils.toString(entity);
            ObjectMapper mapper = new ObjectMapper();

            //JSON file to Java object
            CityWeather cityWeather = mapper.readValue(result, CityWeather.class);

            return cityWeather;
         }

      } catch (IOException e) {
         e.printStackTrace();
      }

      throw new CityWeatherException("Probably couldn't read weather API response.");
   }

   public String getHtmlCityWeather(String city) {
      String url = urlHtmlBuilder(WEATHER_PATH_NAME)
              .queryParam(Q_QUERY, city)
              .build()
              .toUriString();

      HttpGet request = new HttpGet(url);
      try {
         CloseableHttpResponse response = httpClient.execute(request);


         HttpEntity entity = response.getEntity();
         if (entity != null) {
            // return it as a String
            return EntityUtils.toString(entity);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
      return "";
   }

   private UriComponentsBuilder urlHtmlBuilder(String path) {
      return UriComponentsBuilder.fromHttpUrl(HTTP_URL)
              .path(getPath(path))
              .queryParam("units", "metric")
              .queryParam("mode", "html")
              .queryParam(API_KEY_QUERY, System.getenv(API_KEY));
   }


   private UriComponentsBuilder urlBuilder(String path) {
      return UriComponentsBuilder.fromHttpUrl(HTTP_URL)
              .path(getPath(path))
              .queryParam("units", "imperial")
              //  .queryParam("mode", "html")
              .queryParam(API_KEY_QUERY, System.getenv(API_KEY));
   }


   private String getPath(String path) {
      return path.startsWith("/") ? path : "/" + path;
   }

}
