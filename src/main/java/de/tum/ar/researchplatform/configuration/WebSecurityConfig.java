package de.tum.ar.researchplatform.configuration;

import de.tum.ar.researchplatform.component.security.JwtAuthFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.ArrayList;

/**
 * Created by karthik on 9/22/2019
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Force HTTPS
        //http.requiresChannel().anyRequest().requiresSecure();

        // CORS
        ArrayList<String> allowedOriginsList = new ArrayList<>();
        ArrayList<String> allowedMethods = new ArrayList<>();
        allowedOriginsList.add("http://localhost:3000");
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(allowedOriginsList);
        allowedMethods.add("GET");
        allowedMethods.add("POST");
        allowedMethods.add("PUT");
        allowedMethods.add("DELETE");
        corsConfiguration.setAllowedMethods(allowedMethods);
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.applyPermitDefaultValues();
        http
            .cors()
            .configurationSource(request -> corsConfiguration);

        // AUTH
        http
            .csrf().disable().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .and()
            .addFilterAfter(new JwtAuthFilter(),  AnonymousAuthenticationFilter.class);

    }
}
