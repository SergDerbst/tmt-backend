package com.toomanythoughts.tmt.commons.exceptions.logic;

import com.toomanythoughts.tmt.commons.exceptions.TooManyThoughtsException;

public class LogicException extends TooManyThoughtsException {

	private static final long serialVersionUID = -7817277920700634131L;

	protected LogicException() {
		super();
	}

	protected LogicException(final String message) {
		super(message);
	}

	protected LogicException(final Throwable cause) {
		super(cause);
	}

	protected LogicException(final String message,
																		final Throwable cause) {
		super(message, cause);
	}
}
