package com.essaadani.springmvc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.inMemoryAuthentication().withUser("mohamed").password(passwordEncoder.encode("123")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.authorizeRequests().antMatchers("admin**/**", "/save**/**", "/delete**/**", "/form**/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/user**/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.csrf();
        http.exceptionHandling().accessDeniedPage("/notAuthorized");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
