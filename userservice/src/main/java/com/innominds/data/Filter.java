package com.innominds.data;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.innominds.service.MyUserDetailService;

@Component
public class Filter extends OncePerRequestFilter{
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailService myUserDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		final String authorizationHeader=request.getHeader("Authorization");
		
		String token=null;
		String username=null;
		
		if(authorizationHeader!=null && authorizationHeader.startsWith("Bearer "))
		{
		  token=authorizationHeader.substring(7);
		  username=jwtUtil.getUsernameFromToken(token);
//		  email=jwtUtil.getUsernameFromToken(token);

		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=this.myUserDetailsService.loadUserByUsername(username);
		
		if(jwtUtil.validateToken(token, userDetails))
		{
			UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(upat);
			
		}
		
    	}
		filterChain.doFilter(request,response);

    }
}
