package com.jsp.warehouse.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder(12);
		
	}


	@Bean
	public SecurityFilterChain securityFilterchain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.csrf(csrf->csrf.disable())
				.authorizeHttpRequests(authorize->authorize.requestMatchers("api/v1/register").permitAll().anyRequest().authenticated())
				.formLogin(Customizer.withDefaults()).build();


	}
	

}
