package com.example.demo.config;

import com.example.demo.filters.JwtRequestFilter;
import com.example.demo.services.IUserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private IUserService userService;
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfiguration(IUserService userService, JwtRequestFilter jwtRequestFilter){
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().and().csrf().disable()
                 .authorizeRequests().antMatchers("/auth/login", "/auth/registration").permitAll().
                 anyRequest().authenticated().and().  // not() ili anoymous() umesto authenticated()
                 exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // Zrno PasswordEncoder koje u UserContoller-u i AuthController-u koristimo kao servis
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    // Neophodno da bi AuthenticationManager bio ozicen
    public AuthenticationManager authenticationManagerBean() throws Exception {return super.authenticationManagerBean();}

    @Override
    // Za auntetntifikaciju je bitno da se nas userService proglasi za userDetailsService
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
             builder.userDetailsService(userService);
    }
}

