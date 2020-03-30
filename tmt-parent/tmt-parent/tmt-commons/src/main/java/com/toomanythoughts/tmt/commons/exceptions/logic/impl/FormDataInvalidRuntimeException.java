package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class FormDataInvalidRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = -9060171480850506287L;

	private FormDataInvalidRuntimeException() {
		super();
	}

	private FormDataInvalidRuntimeException(final String message) {
		super(message);
	}

	private FormDataInvalidRuntimeException(final Throwable cause) {
		super(cause);
	}

	private FormDataInvalidRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static <T> FormDataInvalidRuntimeException prepare(final String msg, final String name, final T value) {
		return (FormDataInvalidRuntimeException) new FormDataInvalidRuntimeException(msg)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}

	public static <T> FormDataInvalidRuntimeException prepare(final String msg, final String name, final T value, final Throwable cause) {
		return (FormDataInvalidRuntimeException) new FormDataInvalidRuntimeException(msg, cause)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}
}
