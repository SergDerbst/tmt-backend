package com.toomanythoughts.tmt.web.layers.logic.communication.email.impl;

import org.springframework.stereotype.Component;

import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForEmailValidationModel;
import com.toomanythoughts.tmt.web.layers.logic.communication.email.EmailContentBuilder;

@Component
public class RegistrationEmailContentBuilder extends EmailContentBuilder<UserForEmailValidationModel> {

	@Override
	protected String templateName() {
		return "registrationMailTemplate";
	}

	@Override
	protected String messageKeyPrefix() {
		return "registration.mail";
	}
}
