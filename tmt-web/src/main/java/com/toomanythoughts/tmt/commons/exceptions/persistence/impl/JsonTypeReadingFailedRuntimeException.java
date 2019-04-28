package com.toomanythoughts.tmt.commons.exceptions.persistence.impl;

import com.toomanythoughts.tmt.commons.exceptions.persistence.PersistenceRuntimeException;

public class JsonTypeReadingFailedRuntimeException extends PersistenceRuntimeException {

	private static final long serialVersionUID = 5126962828083602622L;

	public static JsonTypeReadingFailedRuntimeException prepare(final Throwable cause, final String value) {
		return (JsonTypeReadingFailedRuntimeException) new JsonTypeReadingFailedRuntimeException()
				.addContextValue("value", value)
				.addContextValue("cause", cause);
	}
}
