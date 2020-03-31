package com.toomanythoughts.tmt.web.layers.logic.communication.email.impl;

import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.authentication.EmailVerificationModel;
import com.toomanythoughts.tmt.web.layers.logic.communication.email.EmailContentBuilder;

@Component
public class RegistrationEmailContentBuilder extends EmailContentBuilder<EmailVerificationModel> {

	@Override
	protected String templateName() {
		return "registrationMailTemplate";
	}

	@Override
	protected String messageKeyPrefix() {
		return "registration.mail";
	}
}
