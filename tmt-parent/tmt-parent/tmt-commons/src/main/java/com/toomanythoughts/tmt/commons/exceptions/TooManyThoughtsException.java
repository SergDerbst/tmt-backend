package com.toomanythoughts.tmt.commons.exceptions;

import org.apache.commons.lang3.exception.ContextedException;

public class TooManyThoughtsException extends ContextedException {

	private static final long serialVersionUID = 1L;

	protected TooManyThoughtsException() {
		super();
	}

	protected TooManyThoughtsException(final String message) {
		super(message);
	}

	protected TooManyThoughtsException(final Throwable cause) {
		super(cause);
	}

	protected TooManyThoughtsException(final String message,
																		final Throwable cause) {
		super(message, cause);
	}
}
