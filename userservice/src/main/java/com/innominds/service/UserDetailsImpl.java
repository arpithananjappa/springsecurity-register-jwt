package com.innominds.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.innominds.data.User;
import com.innominds.document.UserDocument;

public class UserDetailsImpl implements UserDetails{
	
//	public User user;
//	
//	
//
//	public UserDetailsImpl(User user) {
//		super();
//		this.user = user;
//	}
	public UserDocument user;
	
	public UserDetailsImpl(UserDocument user) {
		super();
		this.user=user;
	}
		
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simplegrantedAuthority=new SimpleGrantedAuthority(user.getRole());
		List<SimpleGrantedAuthority>sm=new ArrayList<>();
		sm.add(simplegrantedAuthority);
		return sm;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
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

}
