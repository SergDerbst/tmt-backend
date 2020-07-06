package com.toomanythoughts.tmt.web.logic.auth.authorization.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class CredentialsModel extends EpicPojo {

	private String username;
	private String email;
	private String accessToken;
	private String refreshToken;

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

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
