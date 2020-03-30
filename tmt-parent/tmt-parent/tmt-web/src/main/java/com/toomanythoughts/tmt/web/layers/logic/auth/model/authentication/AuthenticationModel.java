package com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class AuthenticationModel extends EpicPojo {

	private String username;
	private String password;
	private String redirectTo;

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

	public String getRedirectTo() {
		return redirectTo;
	}

	public void setRedirectTo(String redirectTo) {
		this.redirectTo = redirectTo;
	}
}
