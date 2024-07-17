package com.CleanItAg.CleanItAgDemoProject.Login;

import java.security.Principal;
import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@RequestMapping("/login")
	public ResponseEntity<Collection<? extends GrantedAuthority>> login(Principal principal) {
		
		return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
	}
}
