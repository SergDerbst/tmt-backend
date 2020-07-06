package com.toomanythoughts.tmt.web.logic.auth.authentication.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class UsernameValidationModel extends EpicPojo {

	private String username;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
