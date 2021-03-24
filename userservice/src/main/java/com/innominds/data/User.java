package com.innominds.data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.innominds.document.UserDocument;
//@Document(collection="rolebasedemp")

public class User {
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	
	private String id;
	private String username;
	private String department;
	private String email;
	private String password;
	private String role;
	private boolean enabled;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public User() {
		super();
	}
	public User(UserDocument ud) {
		super();
		this.id = ud.getId();
		this.username = ud.getUsername();
		this.department =ud.getDepartment();
		this.email=ud.getEmail();
		this.password=ud.getPassword();
		this.role=ud.getRole();
		this.enabled=ud.isEnabled();
	}
//	public User(String id, String username, String department, String email, String password,String role,boolean enabled) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.department = department;
//		this.email = email;
//		this.password = password;
//		this.role=role;
//		this.enabled=enabled;
//	}
	
	
	

}
