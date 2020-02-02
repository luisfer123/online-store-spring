package com.online.store.security;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.online.store.data.entities.Authority;

public class UtilSecurity {
	
	public static User buildUserDetails(com.online.store.data.entities.User user) {
		return new User(
				user.getUsername(),
				user.getPassword(),
				getAuthorityList(user.getAuthorities()));
	}
	
	public static List<GrantedAuthority> getAuthorityList(Set<Authority> authorities) {
		String stringAuthorities = authorities
				.stream()
				.map(authority -> authority.getAuthority())
				.collect(Collectors.joining(","));
		
		return AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);
	}

}
