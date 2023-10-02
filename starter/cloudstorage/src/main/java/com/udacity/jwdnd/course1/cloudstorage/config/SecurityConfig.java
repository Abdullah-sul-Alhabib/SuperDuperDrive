package com.udacity.jwdnd.course1.cloudstorage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Security config class, configures logins, signups and permissions.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    /**
     * Filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //Permit any request to /login and /signup without need for authentication.
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/signup")
                        ).permitAll()
                )

                //Assign /login as the login page, which means any unauthorized request will be redirected to this one,
                //then redirect successful logins to /home.
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                )

                //Handle logouts
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                        .logoutSuccessUrl("/login?logout").permitAll()
                )

                //Allow only authorized users to /home and /result
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                new AntPathRequestMatcher("/home/**"),
                                new AntPathRequestMatcher("/result"),
                                new AntPathRequestMatcher("/error"),
                                new AntPathRequestMatcher("/file/**"),
                                new AntPathRequestMatcher("/favicon.ico"),
                                new AntPathRequestMatcher("/note/**"),
                                new AntPathRequestMatcher("/credential/**"),
                                new AntPathRequestMatcher("/errors")
                        ).authenticated()
                );

        return http.build();
    }

    /**
     * Web security customizer, tells spring security to ignore requests to static resources.
     *
     * @return the web security customizer
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(
                //I've put them inside AntPathRequestMatcher to resolve error
                new AntPathRequestMatcher("/static/**"),
                new AntPathRequestMatcher("/js/**"),
                new AntPathRequestMatcher("/h2-console/**"),
                new AntPathRequestMatcher("/css/**")
        );
    }
}
