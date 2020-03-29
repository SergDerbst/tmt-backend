package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserCredentialsForRegistrationModel extends EpicPojo {
	private String username;
	private String password;
	private String passwordConfirm;
	private String email;
	private String emailConfirm;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return this.passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailConfirm() {
		return this.emailConfirm;
	}

	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
}