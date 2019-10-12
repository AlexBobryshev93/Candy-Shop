package com.alex.candy_shop.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
  @Qualifier("userRepositoryUserDetailsService")
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public PasswordEncoder encoder() {
    return new StandardPasswordEncoder("53cr3t");
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("home");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encoder());
  }
}
