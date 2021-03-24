package com.innominds.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.innominds.data.AuthenticationRequest;
//import com.innominds.data.JwtUtil;
import com.innominds.data.JwtUtil;
import com.innominds.data.PasswordResetToken;
import com.innominds.data.Token;
import com.innominds.data.User;
import com.innominds.repositories.UserRepository;
import com.innominds.service.MyUserDetailService;
import com.innominds.service.UsersService;

@RestController
public class UserController {
	@Autowired
	UsersService userservice;
	
	@Autowired
	AuthenticationManager authManager;
	
	

	
	
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailService myUserDetailService;
	
	

	@PostMapping("/register")
	public String addUser(@RequestBody User user) {
		return userservice.addUser(user);
	}
	@GetMapping("/users")
	ResponseEntity<List<User>> getAllUser()
	{
		return new ResponseEntity<List<User>>(userservice.getAllUser(),HttpStatus.OK);
	}
	
	
	@PostMapping(value="/authenticate")
	ResponseEntity<Token> createAuthenticate(@RequestBody AuthenticationRequest req) throws Exception
	{
		try 
		{
		authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));

		}
		catch(BadCredentialsException bc)
		{
			throw new Exception("Incorrect userName or password");
		}
		final UserDetails userDetails=myUserDetailService
		.loadUserByUsername(req.getUsername());

		final String token=jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new Token(token));
				
	}

	
	@Autowired
	UserRepository userRepository;
	
	
	
          
	
   
	
	@GetMapping("/users/{id}")
	ResponseEntity<User> getUser(@PathVariable("id")String id)
	{
		return new ResponseEntity<User>(userservice.getUser(id),HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}")
	ResponseEntity<Void> updateUser(@RequestBody User user,@PathVariable("id")String id)
	{
		boolean updated=userservice.updateUser(user,id);
		if(updated)
		return new ResponseEntity<Void>(HttpStatus.OK);
		else

			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/users/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable("id")String id)
	{
		return new ResponseEntity<Void>(userservice.deleteUser(id),HttpStatus.OK);
	}
	
	
	
	



}
