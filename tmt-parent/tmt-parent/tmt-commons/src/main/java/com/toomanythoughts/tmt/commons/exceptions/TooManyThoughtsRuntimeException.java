package com.toomanythoughts.tmt.commons.exceptions;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

@SuppressWarnings("serial")
public abstract class TooManyThoughtsRuntimeException extends ContextedRuntimeException {

	protected TooManyThoughtsRuntimeException() {
		super();
	}

	protected TooManyThoughtsRuntimeException(final String message) {
		super(message);
	}

	protected TooManyThoughtsRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected TooManyThoughtsRuntimeException(final String message,
																		final Throwable cause) {
		super(message, cause);
	}
}
