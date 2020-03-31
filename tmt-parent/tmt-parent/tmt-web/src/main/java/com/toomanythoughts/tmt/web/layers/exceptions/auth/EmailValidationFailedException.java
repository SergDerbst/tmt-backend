package com.toomanythoughts.tmt.web.layers.exceptions.auth;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class EmailValidationFailedException extends ContextedRuntimeException {

	private static final long serialVersionUID = 1L;

	protected EmailValidationFailedException(final String message) {
		super(message);
	}

	public static EmailValidationFailedException prepare(final String username, final String email) {
		return (EmailValidationFailedException) new EmailValidationFailedException("tmt.error.email.validation.failed")
				.addContextValue("username", username)
				.addContextValue("email", email);
	}
}
