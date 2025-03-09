package com.ronald.springboot.cruddemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails ron = User.builder()
                .username("Ron")
                .password("{noop}user123")
                .roles("EMPLOYEE")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}user123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}user123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();


        return new InMemoryUserDetailsManager(ron,mary,susan);
    }
}
