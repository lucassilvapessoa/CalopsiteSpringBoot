package com.calopsite.demo.config;

import com.calopsite.demo.security.JWTAuthenticationFilter;
import com.calopsite.demo.security.JWTAuthorizationFilter;
import com.calopsite.demo.security.JWTUtil;
import com.calopsite.demo.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/users/new**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();
        http.addFilter(new JWTAuthenticationFilter( authenticationManager(), jwtUtil));
        http.addFilter(new JWTAuthorizationFilter( authenticationManager(), jwtUtil, userDetailsServiceImpl));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
       return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}