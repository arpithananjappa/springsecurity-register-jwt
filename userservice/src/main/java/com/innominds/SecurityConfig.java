package com.innominds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

import com.innominds.data.Filter;
import com.innominds.service.MyUserDetailService;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception//to avoid bean creation of AuthenticationManager
	{	
	 return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() 
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public UserDetailsService getUserDetailService() {
		return new MyUserDetailService();
	}
	 
		
	@Autowired
 	private MyUserDetailService myUserDetailsService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());

	}
	@Autowired
	Filter filter;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.csrf().disable();
		
		http
		.authorizeRequests()
		.antMatchers("/register").hasRole("HR")
		.antMatchers("/authenticate").permitAll()
		
		.antMatchers("/users").permitAll()
		.antMatchers("/users/**").hasRole("HR")
		.and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.httpBasic();
		//.and()
		//.formLogin(); // enable form based log in
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

}
