package com.toomanythoughts.tmt.web.layers.exceptions.auth;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.ResourceAlreadyExistsException;

public class UsernameAlreadyExistsException extends ResourceAlreadyExistsException {

	private static final long serialVersionUID = -8727754978234717513L;

	protected UsernameAlreadyExistsException() {
		super();
	}

	protected UsernameAlreadyExistsException(final String message) {
		super(message);
	}

	protected UsernameAlreadyExistsException(final Throwable cause) {
		super(cause);
	}

	protected UsernameAlreadyExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static UsernameAlreadyExistsException prepare(final String username) {
		return (UsernameAlreadyExistsException) new UsernameAlreadyExistsException()
				.addContextValue("username", username);
	}
}
