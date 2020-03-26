package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class DataInvalidRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = -9060171480850506287L;

	private DataInvalidRuntimeException() {
		super();
	}

	private DataInvalidRuntimeException(final String message) {
		super(message);
	}

	private DataInvalidRuntimeException(final Throwable cause) {
		super(cause);
	}

	private DataInvalidRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static <T> DataInvalidRuntimeException prepare(final String msg, final String name, final T value) {
		return (DataInvalidRuntimeException) new DataInvalidRuntimeException(msg)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}

	public static <T> DataInvalidRuntimeException prepare(final String msg, final String name, final T value, final Throwable cause) {
		return (DataInvalidRuntimeException) new DataInvalidRuntimeException(msg, cause)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}
}
