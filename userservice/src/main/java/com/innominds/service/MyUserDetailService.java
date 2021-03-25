package com.innominds.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.innominds.data.AuthenticationRequest;
import com.innominds.data.User;
import com.innominds.document.UserDocument;
import com.innominds.repositories.UserRepository;
@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDocument user=userRepository.findByUsername(username);

		if(user==null) {
			
			throw new UsernameNotFoundException("User  was not found");
		}

		UserDetailsImpl userdetails=new UserDetailsImpl(user);
		return userdetails;
		}

}
