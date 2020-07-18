package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicException;
import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class FormDataInvalidException extends LogicException {

	private static final long serialVersionUID = -9060171480850506287L;

	private FormDataInvalidException() {
		super();
	}

	private FormDataInvalidException(final String message) {
		super(message);
	}

	private FormDataInvalidException(final Throwable cause) {
		super(cause);
	}

	private FormDataInvalidException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public static <T> FormDataInvalidException prepare(final String msg, final String name, final T value) {
		return (FormDataInvalidException) new FormDataInvalidException(msg)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}

	public static <T> FormDataInvalidException prepare(final String msg, final String name, final T value, final Throwable cause) {
		return (FormDataInvalidException) new FormDataInvalidException(msg, cause)
				.addContextValue("name", name)
				.addContextValue("value", value);
	}
}
