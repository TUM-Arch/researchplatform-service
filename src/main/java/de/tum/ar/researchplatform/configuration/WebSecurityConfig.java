package de.tum.ar.researchplatform.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;

/**
 * Created by karthik on 9/22/2019
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ArrayList<String> allowedOriginsList = new ArrayList<>();
        allowedOriginsList.add("http://localhost:3000");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(allowedOriginsList);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.applyPermitDefaultValues();

        http.cors().configurationSource(request -> corsConfiguration);
        http.csrf().disable().authorizeRequests()
                .antMatchers("*").permitAll();
    }
}
