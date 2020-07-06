package com.toomanythoughts.tmt.web.exceptions.content;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class SubtitleInvalidRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = -8685003616915886092L;

	private SubtitleInvalidRuntimeException() {
		super();
	}

	public static SubtitleInvalidRuntimeException prepare(final Long lastStart,
																												final Long lastEnd,
																												final Long newStart,
																												final Long newEnd) {
		return (SubtitleInvalidRuntimeException) new SubtitleInvalidRuntimeException()
				.addContextValue("last subtitle start", lastStart)
				.addContextValue("last subtitle end", lastEnd)
				.addContextValue("new subtitle start", newStart)
				.addContextValue("new subtitle end", newEnd);
	}
}
