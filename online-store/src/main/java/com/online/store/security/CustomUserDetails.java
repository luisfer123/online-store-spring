package com.online.store.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.online.store.data.entities.User;

public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String username;
	
	@JsonIgnore
	private String password;
	
	private String email;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private CustomUserDetails(
			Long id, String username, String password, String email, 
			Collection<? extends GrantedAuthority> authorities) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.authorities = authorities;
		
	}
	
	public static UserUsername builder() {
		return new CustomUserDetailsBuilder();
	}
	
	public static CustomUserDetails build(User user) {
		
		List<GrantedAuthority> authorities = 
				user
					.getAuthorities()
					.stream()
					.map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
					.collect(Collectors.toList());
		
		return new CustomUserDetails(user.getId(), user.getUsername(), user.getPassword(),
				user.getEmail(), authorities);
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getEmail() {
		return this.email;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	interface UserUsername {
		UserPassword username(String username);
	}
	
	interface UserPassword {
		UserAuthorities password(String password);
	}
	
	interface UserAuthorities {
		UserCreator authorities(Collection<? extends GrantedAuthority> authorities);
	}
	
	interface UserCreator {
		UserCreator email(String email);
		UserCreator id(Long id);
		CustomUserDetails build();
	}
	
	public static class CustomUserDetailsBuilder 
			implements UserCreator, UserAuthorities, UserPassword, UserUsername {
		
		private String username;
		
		private String password;
		
		private Collection<? extends GrantedAuthority> authorities;
		
		private Long id;
		
		private String email;
		
		private CustomUserDetailsBuilder() { }

		@Override
		public UserPassword username(String username) {
			this.username = username;
			return this;
		}

		@Override
		public UserAuthorities password(String password) {
			this.password = password;
			return this;
		}

		@Override
		public UserCreator authorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = authorities;
			return this;
		}

		@Override
		public UserCreator email(String email) {
			this.email = email;
			return this;
		}

		@Override
		public UserCreator id(Long id) {
			this.id = id;
			return this;
		}

		@Override
		public CustomUserDetails build() {
			return new CustomUserDetails(id, username, password, email, authorities);
		}
		
		
		
	}

}
