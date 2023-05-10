package net.dunice.newsFeed.config;

import net.dunice.newsFeed.security.jwt.JetConfigure;
import net.dunice.newsFeed.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        private static final String USER_ENDPOINT = "/v1/auth/login";
        private static final String USER_ENDPOINT_REGISTRATION = "/v1/auth/registration";
        private final JwtTokenProvider jwtTokenProvider;

        @Autowired
        public WebSecurityConfig(JwtTokenProvider jwtTokenProvider){
            this.jwtTokenProvider = jwtTokenProvider;
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception{
            return super.authenticationManagerBean();
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
                .antMatchers(USER_ENDPOINT, USER_ENDPOINT_REGISTRATION).permitAll()
                .anyRequest().authenticated()
                .and().apply(new JetConfigure(jwtTokenProvider));
    }
}