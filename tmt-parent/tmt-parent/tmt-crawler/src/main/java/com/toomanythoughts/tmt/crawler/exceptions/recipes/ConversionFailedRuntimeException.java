package com.toomanythoughts.tmt.crawler.exceptions.recipes;

import com.toomanythoughts.tmt.crawler.exceptions.TmtCrawlRuntimeException;

public class ConversionFailedRuntimeException extends TmtCrawlRuntimeException {

	private static final long serialVersionUID = 5414640555547614117L;

	private ConversionFailedRuntimeException(final String message) {
		super(message);
	}

	public static ConversionFailedRuntimeException prepare(final String message, final String ingredient) {
		return (ConversionFailedRuntimeException) new ConversionFailedRuntimeException(message).addContextValue("ingredient", ingredient);
	}
}
