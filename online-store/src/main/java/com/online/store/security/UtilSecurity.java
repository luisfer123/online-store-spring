package com.online.store.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.online.store.data.entities.Authority;
import com.online.store.data.entities.User;

public class UtilSecurity {
	
	public static String getPrincipalUsername() {
		
		Object principal =
				SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		
		String principalUsername;
		
		if(principal instanceof UserDetails)
			principalUsername = ((UserDetails) principal).getUsername();
		else
			principalUsername = principal.toString();
		
		return principalUsername;
	}
	
	public static CustomUserDetails buildUserDetails(User user) {
		return CustomUserDetails.build(user);
	}
	
	public static List<GrantedAuthority> getAuthorityList(Set<Authority> authorities) {
		String stringAuthorities = authorities
				.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
				
		return AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
	}

}
