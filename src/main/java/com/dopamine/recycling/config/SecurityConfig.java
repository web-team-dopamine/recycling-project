package com.dopamine.recycling.config;

import com.dopamine.recycling.domain.constant.Role;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
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

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/", "/login", "/logout", "/user/**").permitAll()
//                        .requestMatchers("/admin/**").hasAnyRole(Role.ROLE_ADMIN.getName())
//                        .requestMatchers("/seller/**").hasAnyRole(Role.ROLE_SELLER.getName(), Role.ROLE_ADMIN.getName())
//                        .anyRequest().authenticated())
//                .formLogin(auth -> auth
//                        .loginPage("/login")  // 로그인 페이지
//                        .loginProcessingUrl("/login")  // 로그인 처리 URL
//                        .defaultSuccessUrl("/", true)
//                        .failureUrl("/login?error=true")
//                        .permitAll())
//                .logout(auth -> auth
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/")
//                        .invalidateHttpSession(true)
//                        .permitAll())
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll())  // 모든 요청을 허용
                .csrf(AbstractHttpConfigurer::disable)  // CSRF 보호 비활성화
                .build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
