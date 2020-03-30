package com.toomanythoughts.tmt.commons.exceptions.logic.impl.auth;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class AuthenticationFailedRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = -2597592445740762415L;

	protected AuthenticationFailedRuntimeException() {
		super();
	}

	protected AuthenticationFailedRuntimeException(final String message) {
		super(message);
	}

	protected AuthenticationFailedRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected AuthenticationFailedRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static AuthenticationFailedRuntimeException prepare() {
		return new AuthenticationFailedRuntimeException();
	}
}
