package com.toomanythoughts.tmt.commons.exceptions.persistence.impl;

import com.toomanythoughts.tmt.commons.exceptions.persistence.PersistenceRuntimeException;

public class JsonTypeWritingFailedRuntimeException extends PersistenceRuntimeException {

	private static final long serialVersionUID = -1123570806187815007L;

	public static JsonTypeWritingFailedRuntimeException prepare(final Throwable cause, final Object value) {
		return (JsonTypeWritingFailedRuntimeException) new JsonTypeWritingFailedRuntimeException()
				.addContextValue("cause", cause)
				.addContextValue("value", value);
	}
}
