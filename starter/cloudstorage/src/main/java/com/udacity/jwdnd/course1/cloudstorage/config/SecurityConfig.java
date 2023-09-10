package com.udacity.jwdnd.course1.cloudstorage.config;


import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;

/**
 * Security config class, configures logins, signups and permissions.
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Permit any request to /login and /signup without need for authentication.
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup")).permitAll()
                )

        //Assign /login as the login page, which means any unauthorized request will be redirected to this one,
        //then redirect successful logins to /home.
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                )

        //Handle logouts
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout").permitAll()
                )

        //Allow only authorized (logged in) users to /home and /result
                .authorizeHttpRequests((authz) -> authz
                .requestMatchers(
                        new AntPathRequestMatcher("/home"),
                        new AntPathRequestMatcher("/result")).authenticated()
                );

        return http.build();
    }

    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/js/**",
                "/css/**"
        );
    }
}
