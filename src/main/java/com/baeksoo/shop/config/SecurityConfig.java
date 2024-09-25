package com.baeksoo.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.authorizeHttpRequests((authorize) -> authorize
//                .requestMatchers("/admin/**").hasRole("ADMIN") // 관리자만 접근 허용
                .anyRequest().permitAll() // 나머지는 모두 접근 가능
        );
        http.formLogin((formLogin) -> formLogin
                .loginPage("/signIn")
                .defaultSuccessUrl("/")
        );
        http.logout( logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );
        return http.build();
    }
}
