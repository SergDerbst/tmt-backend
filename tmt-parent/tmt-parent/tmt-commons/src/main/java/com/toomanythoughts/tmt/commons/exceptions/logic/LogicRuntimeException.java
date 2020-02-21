package com.toomanythoughts.tmt.commons.exceptions.logic;

import com.toomanythoughts.tmt.commons.exceptions.TooManyThoughtsRuntimeException;

public abstract class LogicRuntimeException extends TooManyThoughtsRuntimeException {

	private static final long serialVersionUID = 4932522221000425259L;

	protected LogicRuntimeException() {
		super();
	}

	protected LogicRuntimeException(final String message) {
		super(message);
	}

	protected LogicRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected LogicRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
