package com.postGre.bsHive.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    BCryptPasswordEncoder endodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
									.anyRequest()
									.permitAll())
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable());
		
		return httpSecurity.build(); 
	}
	
    
    /*
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable());
				
		return http.build(); 
	}
    */
    
    
	
}
