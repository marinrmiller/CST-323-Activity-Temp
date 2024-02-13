package com.gcu.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public class UserEntity {
	
	@Id
	@Column("userID")
	private int userID;
	
	@Column("username")
	private String username;
	
	@Column("password")
	private String password;
	
	@Column("firstName")
	private String firstName;
	
	@Column("lastName")
	private String lastName;
	
	@Column("email")
	private String email;
	
	@Column("phoneNumber")
	private String phoneNumber;

	public UserEntity() {
		
	}
	//For login
	public UserEntity(String username, String password) {
		super();
		this.userID = 0;
		this.username = username;
		this.password = password;
		this.firstName = "Unknown";
		this.lastName = "Unknown";
		this.email = "Unknown";
		this.phoneNumber = "Unknown";
	}

	public UserEntity(String username, String firstName, String lastName, String email, String password, String phoneNumber) {
		super();
		this.userID = 0;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	public UserEntity(int userId, String username, String firstName, String lastName, String email, String password, String phoneNumber) {
		super();
		this.userID = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userID;
	}

	public void setUserId(int userId) {
		this.userID = userId;
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
