package com.toomanythoughts.tmt.commons.exceptions.config;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class ConfigRuntimeException extends ContextedRuntimeException {

	private static final long serialVersionUID = 778522893156471734L;

	protected ConfigRuntimeException() {
		super();
	}

	protected ConfigRuntimeException(final String message) {
		super(message);
	}

	protected ConfigRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected ConfigRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
