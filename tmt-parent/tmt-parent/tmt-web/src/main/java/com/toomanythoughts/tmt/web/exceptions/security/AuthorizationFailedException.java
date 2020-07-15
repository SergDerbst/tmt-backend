package com.toomanythoughts.tmt.web.exceptions.security;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicException;

public class AuthorizationFailedException extends LogicException {

	private static final long serialVersionUID = -2597592445740762415L;

	protected AuthorizationFailedException() {
		super();
	}

	protected AuthorizationFailedException(final String message) {
		super(message);
	}

	protected AuthorizationFailedException(final Throwable cause) {
		super(cause);
	}

	protected AuthorizationFailedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static AuthorizationFailedException prepare(final String username) {
		return (AuthorizationFailedException) new AuthorizationFailedException()
				.addContextValue("username", username);
	}

	public static Exception prepare(Throwable cause) {
		return new AuthorizationFailedException(cause);
	}
}
