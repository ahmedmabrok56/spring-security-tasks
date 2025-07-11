package com.dailycodework.security.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/hello")
                        .authenticated()
                        .anyRequest()
                        .permitAll()

                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/hello" , true)
                )
                .logout(logout -> logout.permitAll());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("Ahmed")
                .password("AhmedMabrok")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
