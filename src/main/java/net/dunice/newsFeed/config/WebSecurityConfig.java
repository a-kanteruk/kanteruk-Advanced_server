package net.dunice.newsFeed.config;

import lombok.RequiredArgsConstructor;
import net.dunice.newsFeed.constants.EndpointConstants;
import net.dunice.newsFeed.security.jwt.JwtConfigure;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private final JwtTokenProvider jwtTokenProvider;

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public PasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.httpBasic()
                .disable()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(EndpointConstants.USER_ENDPOINT,
                             EndpointConstants.USER_ENDPOINT_REGISTRATION,
                             EndpointConstants.USER_ENDPOINT_NEWS).permitAll()
                .anyRequest().authenticated()
                .and().apply(new JwtConfigure(jwtTokenProvider));
    }
}
