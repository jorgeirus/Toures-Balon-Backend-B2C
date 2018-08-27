package com.javeriana.pica.Toures.Balon.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.javeriana.pica.Toures.Balon.service.AppUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AppUserDetailsService appUserDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(appUserDetailsService);
	}



	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			public void addCorsMappings(CorsRegistry corsRegistry) {
				corsRegistry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
		// starts authorizing configurations
		.authorizeRequests()
		// ignoring the guest's urls "
		.antMatchers("/account/register","/account/login","/logout").permitAll()
		// authenticate all remaining URLS
		.anyRequest().fullyAuthenticated().and()
		 /* "/logout" will log the user out by invalidating the HTTP Session,
	       * cleaning up any {link rememberMe()} authentication that was configured, */
		.logout()
		.permitAll()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
		.and()
		// enabling the basic authentication
		.httpBasic().and()
		// configuring the session on the server
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
		// disabling the CSRF - Cross Site Request Forgery
		.csrf().disable();
		
	}
	
	
	
}
