package com.toomanythoughts.tmt.web.layers.logic.auth.model.user;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UserCredentialsModel extends EpicPojo {

	private String username;
	private String email;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
