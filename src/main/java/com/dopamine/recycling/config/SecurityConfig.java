package com.dopamine.recycling.config;

import com.dopamine.recycling.domain.constant.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer configure() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest
                        .toStaticResources()
                        .atCommonLocations()
                );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/", "/login", "/user/**").permitAll()
                                .requestMatchers("/admin/**").hasAnyRole(Role.ROLE_ADMIN.getName())
                                .requestMatchers("/seller/**").hasAnyRole(Role.ROLE_SELLER.getName(), Role.ROLE_ADMIN.getName())
                                .anyRequest().authenticated())
                .formLogin(auth -> auth.loginPage("/login")
                        .defaultSuccessUrl("/",true))
                .logout(auth -> auth.logoutSuccessUrl("/")
                        .invalidateHttpSession(true))
                .csrf(auth -> auth.disable())
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
