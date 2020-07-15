package com.toomanythoughts.tmt.web.logic.security.authentication.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class AuthenticatedModel extends EpicPojo {

	private String username;
	private String token;


	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
