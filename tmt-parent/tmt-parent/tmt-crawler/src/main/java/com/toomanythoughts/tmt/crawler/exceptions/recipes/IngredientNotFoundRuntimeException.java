package com.toomanythoughts.tmt.crawler.exceptions.recipes;

import com.toomanythoughts.tmt.crawler.exceptions.TmtCrawlRuntimeException;

public class IngredientNotFoundRuntimeException extends TmtCrawlRuntimeException {

	private static final long serialVersionUID = -4913805800131318935L;

	private IngredientNotFoundRuntimeException() {
		super();
	}

	private IngredientNotFoundRuntimeException(final String message) {
		super(message);
	}

	public static IngredientNotFoundRuntimeException prepare(final String message, String key) {
		return (IngredientNotFoundRuntimeException) new IngredientNotFoundRuntimeException(message).addContextValue("key", key);
	}
}
