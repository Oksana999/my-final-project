package com.example.myfinalproject.config;

import com.example.myfinalproject.config.AuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Oksana
 */
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

   // Create spring-managed object to allow the app to access our filter
   @Bean
   public AuthenticationFilter authenticationFilter() {
      return new AuthenticationFilter();
   }

   // Register the filter with the Spring container
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor( authenticationFilter() );
   }

}
