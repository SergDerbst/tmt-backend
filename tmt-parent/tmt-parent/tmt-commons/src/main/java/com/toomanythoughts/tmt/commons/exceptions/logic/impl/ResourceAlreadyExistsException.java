package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicException;

public abstract class ResourceAlreadyExistsException extends LogicException {

	private static final long serialVersionUID = 489562785371323676L;

	protected ResourceAlreadyExistsException() {
		super();
	}

	protected ResourceAlreadyExistsException(final String message) {
		super(message);
	}

	protected ResourceAlreadyExistsException(final Throwable cause) {
		super(cause);
	}

	protected ResourceAlreadyExistsException(final String message,
																		final Throwable cause) {
		super(message, cause);
	}
}
