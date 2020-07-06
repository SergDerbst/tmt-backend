package com.toomanythoughts.tmt.web.exceptions.auth;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class AuthenticationFailedException extends LogicRuntimeException {

	private static final long serialVersionUID = -2597592445740762415L;

	protected AuthenticationFailedException() {
		super();
	}

	protected AuthenticationFailedException(final String message) {
		super(message);
	}

	protected AuthenticationFailedException(final Throwable cause) {
		super(cause);
	}

	protected AuthenticationFailedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static AuthenticationFailedException prepare() {
		return new AuthenticationFailedException();
	}
}
