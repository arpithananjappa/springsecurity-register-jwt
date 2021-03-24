package com.innominds.data;

public class PasswordResetToken {
	private String id;
	private  User user;
	private String token;
	private String expirydate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getExpirydate() {
		return expirydate;
	}
	
	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}
	public PasswordResetToken() {
		super();
	}
	public PasswordResetToken(String id, User user, String token, String expirydate) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
		this.expirydate = expirydate;
	}
	

}
