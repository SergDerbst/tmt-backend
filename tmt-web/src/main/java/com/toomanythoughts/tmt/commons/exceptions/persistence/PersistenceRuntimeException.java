package com.toomanythoughts.tmt.commons.exceptions.persistence;

import org.apache.commons.lang3.exception.ExceptionContext;

import com.toomanythoughts.tmt.commons.exceptions.TooManyThoughtsRuntimeException;

@SuppressWarnings("serial")
public abstract class PersistenceRuntimeException extends TooManyThoughtsRuntimeException {

	protected PersistenceRuntimeException() {
		super();
	}

	protected PersistenceRuntimeException(final String message) {
		super(message);
	}

	protected PersistenceRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected PersistenceRuntimeException(	final String message,
																	final Throwable cause) {
		super(message,
					cause);
	}

	protected PersistenceRuntimeException(	final String message,
																	final Throwable cause,
																	final ExceptionContext context) {
		super(message);
	}
}
