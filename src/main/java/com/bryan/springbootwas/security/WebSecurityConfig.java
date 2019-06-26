package com.bryan.springbootwas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	//clase que restringe los servicios.
	
	@Autowired
    private CustomAuthenticationProvider authProvider;
	
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .antMatchers(HttpMethod.POST, "/login", "/crypt/**").permitAll()
            .anyRequest().authenticated()
            .and()
            // We filter the api/login requests
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            // And filter other requests to check the presence of JWT in header
            .addFilterBefore(new JWTAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Create a default account
//    authProvider contiene la logica que valida los users y pwd
    auth.authenticationProvider(authProvider);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**");
  }
}
