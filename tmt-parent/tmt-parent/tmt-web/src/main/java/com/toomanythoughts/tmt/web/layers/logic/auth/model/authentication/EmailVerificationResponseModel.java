package com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class EmailVerificationResponseModel extends EpicPojo {

	private boolean verified;

	public EmailVerificationResponseModel(boolean verified) {
		this.verified = verified;
	}

	public boolean isVerified() {
		return this.verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
}
