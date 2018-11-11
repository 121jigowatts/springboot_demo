package com.example.springboot_demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.springboot_demo.services.AccountService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	AccountService service;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().and().formLogin()
		// .loginPage("/login").failureUrl("/login?error=true").usernameParameter("name")
		// .passwordParameter("password").and().logout().logoutRequestMatcher(new
		// AntPathRequestMatcher("/logout"))
		// .logoutSuccessUrl("/").and().exceptionHandling();
		http.authorizeRequests().antMatchers("/login","/signup","/api/user/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login");
	}

	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(service);
	}
}
