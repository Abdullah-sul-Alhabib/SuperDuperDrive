package com.udacity.jwdnd.course1.cloudstorage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Security config class, configures logins, signups and permissions.
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(new AntPathRequestMatcher("/signup"))
                        .permitAll()
                        .anyRequest().authenticated());
        http
                .formLogin( form -> form
                        .loginPage("/login")
                        .permitAll()
                );

        http
                .formLogin(form -> form
                        .defaultSuccessUrl("/home", true)
                );
//        http
//                .authorizeRequests()
        return http.build();
    }


}
