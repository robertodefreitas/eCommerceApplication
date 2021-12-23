package com.example.demo.model.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.controllers.UserController;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserRequest {

	private static final Logger logger = LoggerFactory.getLogger(CreateUserRequest.class);

	@JsonProperty
	private String username;

	@JsonProperty
	private String password;

	@JsonProperty
	private String confirmPassword;

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}
