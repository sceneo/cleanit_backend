package com.cleanit.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.cleanit.UserDetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cleanit.Employee.EmployeeRepository;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfiguration {
	
	@Autowired
    private UserDetailsServiceImpl userDetailsService;
	
	private EmployeeRepository employeeRepository;
	
	public SpringSecurityConfiguration(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(AbstractHttpConfigurer::disable);
		
		http.authenticationProvider(authProvider());
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		http.httpBasic(withDefaults());
		
		//POST or PUT
		http.csrf(AbstractHttpConfigurer::disable);

		//for H2 console
		http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

		return http.build();
	}
}
