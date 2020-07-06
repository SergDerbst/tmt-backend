package com.toomanythoughts.tmt.web.logic.auth.authentication.model;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.UserModel;

public class AuthenticatedModel extends EpicPojo {

	private UserModel user;
	private String redirectTo;

	public UserModel getUser() {
		return this.user;
	}

	public void setUser(final UserModel user) {
		this.user = user;
	}

	public String getRedirectTo() {
		return this.redirectTo;
	}

	public void setRedirectTo(final String redirectTo) {
		this.redirectTo = redirectTo;
	}
}
