//package com.example.myfinalproject.config;
//
//import com.example.myfinalproject.models.Role;
//import com.example.myfinalproject.models.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//
///**
// * @author Oksana
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//   @Override
//   public void configure(final WebSecurity web) throws Exception {
//      web.ignoring().antMatchers("/resources/**");
//   }
//
//   @Override
//   protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//      auth.authenticationProvider();
//
//   }
//
//   @Override
//   protected void configure(HttpSecurity http) throws Exception {
//      http
//              .csrf().disable()
//              .authorizeRequests()
//              .antMatchers("/admin")
//              .hasAnyRole(Role.ADMIN.name())
//
//              .and()
//
//              .authorizeRequests()
//              .antMatchers("/login/**", "/register", "/css/**", "/logout/**")
//              .permitAll()
//              .anyRequest().hasAuthority(Role.USER.name())
//
//              .and()
//
//              .formLogin()
//              .loginPage("/login.html")
//              .loginProcessingUrl("/login")
//              .defaultSuccessUrl("/home")
//              .failureUrl("/login")
//              .permitAll();
//   }
//}
