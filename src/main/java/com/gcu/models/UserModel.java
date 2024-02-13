package com.gcu.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	
	private int userId;
	
	@NotNull(message="Username is a required field")
	@Size(min=1, max=32, message="Username must be between 1 and 32 characters")
	private String username;
	
	@NotNull(message="Password is a required field")
	@Size(min=1, max=32, message="Password must be between 1 and 32 characters")
	private String password;

	@NotNull(message="First Name is a required field")
	@Size(min=1, max=32, message="First Name must be between 1 and 32 characters")
	private String firstName;
	@NotNull(message="Last Name is a required field")
	@Size(min=1, max=32, message="Last Name must be between 1 and 32 characters")
	private String lastName;

	@NotNull(message="Email is a required field")
	@Size(min=5, max=320, message="Email must be between 5 and 320 characters")
	private String email;

	@NotNull(message="Phone Number is a required field")
	@Size(min=14, max=14, message="Phone Number must be 14 characters")
	private String phoneNumber;
	
	public UserModel(int userId,
			String username,
			String password,
			String firstName,
			String lastName,
			String email,
			String phoneNumber) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public UserModel() {
	}

	public int getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
