package com.mamingjie.lovejr.config;

import com.mamingjie.lovejr.service.DefaultUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/", "/index").permitAll()
                .antMatchers("/public/**").permitAll()
                .anyRequest().fullyAuthenticated().and().formLogin()
                .loginPage("/user/login").failureUrl("/user/login?error").permitAll().and()
                .logout().logoutUrl("/user/logout").permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        provider.setSaltSource(saltSource());
        auth.authenticationProvider(provider);
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        //auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        //auth.authenticationProvider()
        /*auth.inMemoryAuthentication().withUser("admin").password("1120")
                .roles("ADMIN", "USER").and().withUser("user").password("user")
                .roles("USER");*/
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder() {
        return new Md5PasswordEncoder();
    }

    @Bean
    public DefaultUserDetailsService userDetailsService() {
        return new DefaultUserDetailsService();
    }

    @Bean
    public SaltSource saltSource() {
        return new SaltSource() {
            @Override
            public Object getSalt(UserDetails userDetails) {
                return "test!@#salt";
            }
        };
    }
}
