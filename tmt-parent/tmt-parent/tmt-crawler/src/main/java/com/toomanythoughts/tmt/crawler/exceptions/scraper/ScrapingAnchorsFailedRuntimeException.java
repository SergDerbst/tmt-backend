package com.toomanythoughts.tmt.crawler.exceptions.scraper;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class ScrapingAnchorsFailedRuntimeException extends ContextedRuntimeException {

	private static final long serialVersionUID = 5720886721560197689L;

	private ScrapingAnchorsFailedRuntimeException() {
		super();
	}

	private ScrapingAnchorsFailedRuntimeException(Throwable cause) {
		super(cause);
	}

	public static final ScrapingAnchorsFailedRuntimeException prepare(final String href, final String root, final Throwable cause) {
		return (ScrapingAnchorsFailedRuntimeException) new ScrapingAnchorsFailedRuntimeException(cause)
				.addContextValue("href", href)
				.addContextValue("root", root);
	}
}
