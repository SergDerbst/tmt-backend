package com.toomanythoughts.tmt.web.exceptions.security;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.ResourceAlreadyExistsException;

public class EmailAlreadyExistsException extends ResourceAlreadyExistsException {

	private static final long serialVersionUID = -364744664416210487L;

	protected EmailAlreadyExistsException() {
		super();
	}

	protected EmailAlreadyExistsException(final String message) {
		super(message);
	}

	protected EmailAlreadyExistsException(final Throwable cause) {
		super(cause);
	}

	protected EmailAlreadyExistsException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static EmailAlreadyExistsException prepare(final String email) {
		return (EmailAlreadyExistsException) new EmailAlreadyExistsException()
				.addContextValue("email", email);
	}
}
