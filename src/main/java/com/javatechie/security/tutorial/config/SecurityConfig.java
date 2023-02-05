package com.javatechie.security.tutorial.config;

import com.javatechie.security.tutorial.config.service.UserInfoUserDetailsService;
import com.javatechie.security.tutorial.repositories.UserInfoRepository;
import com.mysql.cj.protocol.AuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(){

        // ========= HARDCODED USER-DETAILS  ==========

//        UserDetails admin = User.withUsername("Axel")
//                .password(encoder.encode("pass1"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("Peter")
//                .password(encoder.encode("pass2"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);

        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/products/welcome", "/accounts/add-user").permitAll()
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/products/**").authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
