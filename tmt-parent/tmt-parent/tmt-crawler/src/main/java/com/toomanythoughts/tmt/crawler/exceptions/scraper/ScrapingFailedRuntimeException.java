package com.toomanythoughts.tmt.crawler.exceptions.scraper;

import com.toomanythoughts.tmt.crawler.exceptions.TmtCrawlRuntimeException;

public class ScrapingFailedRuntimeException extends TmtCrawlRuntimeException {

	private static final long serialVersionUID = 7746707615305993496L;

	private ScrapingFailedRuntimeException() {
		super();
	}

	private ScrapingFailedRuntimeException(final Throwable cause) {
		super(cause);
	}

	public static ScrapingFailedRuntimeException prepare(final String url, final Throwable cause) {
		return (ScrapingFailedRuntimeException) new ScrapingFailedRuntimeException(cause).addContextValue("url", url);
	}
}
