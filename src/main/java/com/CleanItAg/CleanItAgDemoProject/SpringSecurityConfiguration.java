package com.CleanItAg.CleanItAgDemoProject;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.CleanItAg.CleanItAgDemoProject.Employee.Employee;
import com.CleanItAg.CleanItAgDemoProject.Employee.EmployeeRepository;

import jakarta.annotation.Resource;

import com.CleanItAg.CleanItAgDemoProject.Employee.Employee.Role;

import org.springframework.security.core.userdetails.User;

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

//	@Bean
//	public InMemoryUserDetailsManager createUserDetailsManager() {
//		
//		UserDetails userDetails1 = new Employee(1, "Employeename1", "employee1@mail.com", "employee1 street nr city code", "+41 123 456 789", Role.employee, "123");
//		//UserDetails userDetails2 = createNewUser("employee", "123");
//		
//		return new InMemoryUserDetailsManager(userDetails1);
//	}
//
//	private UserDetails createNewUser(String username, String password) {
//		Function<String, String> passwordEncoder
//		= input -> passwordEncoder().encode(input);
//
//		UserDetails userDetails = User.builder()
//									.passwordEncoder(passwordEncoder)
//									.username(username)
//									.password(password)
//									.roles("USER","ADMIN")
//									.build();
//		return userDetails;
//	}

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
		http.cors(cors -> cors.disable());
		
		http.authenticationProvider(authProvider());
		
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		
		http.httpBasic(withDefaults());
		
		http.csrf(csrf -> csrf.disable()); //POST or PUT
		
		http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable())); //for H2 console
		
		return http.build();
	}
}
