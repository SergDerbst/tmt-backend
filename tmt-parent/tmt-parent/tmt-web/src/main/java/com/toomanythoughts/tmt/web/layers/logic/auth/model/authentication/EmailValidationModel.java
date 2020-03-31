package com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class EmailValidationModel extends EpicPojo {

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
