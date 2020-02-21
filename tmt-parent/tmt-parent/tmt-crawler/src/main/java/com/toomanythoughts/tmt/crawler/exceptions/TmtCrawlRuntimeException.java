package com.toomanythoughts.tmt.crawler.exceptions;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public abstract class TmtCrawlRuntimeException extends ContextedRuntimeException {

	private static final long serialVersionUID = 7520831057755620405L;

	protected TmtCrawlRuntimeException() {
		super();
	}

	protected TmtCrawlRuntimeException(final String message) {
		super(message);
	}

	protected TmtCrawlRuntimeException(final Throwable cause) {
		super(cause);
	}

	protected TmtCrawlRuntimeException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
