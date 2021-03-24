package com.innominds.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.innominds.data.AuthenticationRequest;
import com.innominds.data.PasswordResetToken;
import com.innominds.data.User;
import com.innominds.document.UserDocument;
//import com.innominds.document.UserDocument;
import com.innominds.repositories.UserRepository;

@Service
public class UsersService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder passwordencoder;


	
	public String addUser(User user) {
		UserDocument x=new UserDocument();
		if(user.getUsername()!=null)
			x.setUsername(user.getUsername());
		if(user.getDepartment()!=null)
			x.setDepartment(user.getDepartment());
		if(user.getEmail()!=null)
			x.setEmail(user.getEmail());
		if(user.getPassword()!=null)
//			x.setPassword(user.getPassword());
			x.setPassword(passwordencoder.encode(user.getPassword()));
		if(user.getRole()!=null)
			x.setRole(user.getRole());
		x.setEnabled(true);
		userRepository.save(x);
		return "Registerd successfully";
	}

	public List<User> getAllUser() {
		List<UserDocument> ud=userRepository.findAll();
		List<User>user=new ArrayList<>();//used to covert User to List<User>

		for(UserDocument pe:ud)
		{
		user.add(new User(pe));
		}
		return user;		
	}

	public User getUser(String id) {
		Optional<UserDocument> userdocument=userRepository.findById(id);
		if(userdocument.isPresent()) {
			return new User(userdocument.get());
		}
		return null;
	}
	

	
	public boolean updateUser(User user,String id) {
		Optional<UserDocument> userdocument=userRepository.findById(id);
		if(userdocument.isPresent()) {
			if(user.getId()!=null)
			    userdocument.get().setId(user.getId());
			if(user.getUsername()!=null)
				userdocument.get().setUsername(user.getUsername());
			if(user.getDepartment()!=null)
				userdocument.get().setDepartment(user.getDepartment());
			if(user.getEmail()!=null)
				userdocument.get().setEmail(user.getEmail());
			if(user.getPassword()!=null)
				userdocument.get().setPassword(user.getPassword());
			userRepository.save(userdocument.get());
			
			
		}
		return false;
	}

	public Void deleteUser(String id) {
		Optional<UserDocument> userdocument=userRepository.findById(id);
		if(userdocument.isPresent()) {
			new User(userdocument.get());
			userRepository.deleteById(id);
			
		}
		

		return null;
	}
	
	
	

	
		


}
