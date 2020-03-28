package com.toomanythoughts.tmt.commons.exceptions.logic.impl.auth;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class EmailValidationFailedRuntimeException extends ContextedRuntimeException {

	private static final long serialVersionUID = 1L;

	protected EmailValidationFailedRuntimeException(final String message) {
		super(message);
	}

	public static EmailValidationFailedRuntimeException prepare(final String username, final String email) {
		return (EmailValidationFailedRuntimeException) new EmailValidationFailedRuntimeException("tmt.error.email.validation.failed")
				.addContextValue("username", username)
				.addContextValue("email", email);
	}
}
