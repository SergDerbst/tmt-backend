package com.toomanythoughts.tmt.crawler.exceptions.crawler;

import com.toomanythoughts.tmt.crawler.exceptions.TmtCrawlRuntimeException;

public class InitializingCrawerFailedRuntimeException extends TmtCrawlRuntimeException {

	private static final long serialVersionUID = -2785213619328254158L;

	private InitializingCrawerFailedRuntimeException() {
		super();
	}

	private InitializingCrawerFailedRuntimeException(final Throwable cause) {
		super(cause);
	}

	public static InitializingCrawerFailedRuntimeException prepare(final String url, Throwable cause) {
		return (InitializingCrawerFailedRuntimeException) new InitializingCrawerFailedRuntimeException(cause).addContextValue("URL", url);
	}
}
