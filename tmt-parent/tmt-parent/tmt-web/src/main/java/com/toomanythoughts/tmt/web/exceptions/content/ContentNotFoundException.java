package com.toomanythoughts.tmt.web.exceptions.content;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicException;
import com.toomanythoughts.tmt.web.logic.content.ContentType;

public class ContentNotFoundException extends LogicException {

	private static final long serialVersionUID = 4688778438841508053L;

	protected ContentNotFoundException() {
		super();
	}

	protected ContentNotFoundException(final String message) {
		super(message);
	}

	public static ContentNotFoundException prepare(final String message,
																							 final ContentType type,
																							 final Integer id,
																							 final String title) {
		return (ContentNotFoundException) new ContentNotFoundException(message)
				.addContextValue("type", type)
				.addContextValue("id", id)
				.addContextValue("title", title);
	}
}
