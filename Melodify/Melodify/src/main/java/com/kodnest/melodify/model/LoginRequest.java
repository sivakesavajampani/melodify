package com.kodnest.tunehub.model;

public class LoginRequest {
	String nameorEmail;
	String password;
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginRequest(String nameorEmail, String password) {
		super();
		this.nameorEmail = nameorEmail;
		this.password = password;
	}
	public String getNameorEmail() {
		return nameorEmail;
	}
	public void setNameorEmail(String nameorEmail) {
		this.nameorEmail = nameorEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginRequest [nameorEmail=" + nameorEmail + ", password=" + password + "]";
	}
	
	

}
