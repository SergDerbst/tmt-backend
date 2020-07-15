package com.toomanythoughts.tmt.web.logic.security.authentication.model;

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
