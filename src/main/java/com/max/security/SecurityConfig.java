package com.max.security;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    Logger logger = Logger.getLogger(SecurityConfig.class);

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("123123")).roles("ADMIN", "USER")
            .and()
                .withUser("user").password(passwordEncoder().encode("321321")).roles("USER");


        logger.info("Spring security auth initialized successfully");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/customer/list").authenticated()
                .antMatchers("/customer/**").hasRole("ADMIN")
            .and()
                .formLogin()
                        .loginPage("/login")
                        .loginProcessingUrl("/processLogin")
                .permitAll()
            .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                .permitAll()
            .and()
                .exceptionHandling().accessDeniedPage("/error/forbidden");

        logger.info("Spring security http initialized successfully");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
