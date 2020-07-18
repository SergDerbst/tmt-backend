package com.toomanythoughts.tmt.web.logic.content.transcript.subtitles;

import java.util.ArrayList;

import com.toomanythoughts.tmt.web.exceptions.content.SubtitleInvalidRuntimeException;

public class Subtitles extends ArrayList<Subtitle> {

	private static final long serialVersionUID = 2118100608203110507L;

	@Override
	public boolean add(Subtitle newSub) {
		this.checkValidity(this.get(this.size() - 1), newSub);
		return super.add(newSub);
	}

	private void checkValidity(Subtitle lastSub, Subtitle newSub) {
		if (this.startIsBeforeLastStart(lastSub, newSub) ||
				this.startIsBeforeLastEnd(lastSub, newSub) ||
				this.endIsBeforeStart(newSub)) {
			throw SubtitleInvalidRuntimeException.prepare(lastSub.getStart(), lastSub.getEnd(), newSub.getStart(), newSub.getEnd());
		}
	}

	private boolean startIsBeforeLastStart(Subtitle lastSub, Subtitle newSub) {
		return lastSub.getStart().compareTo(newSub.getStart()) >= 0;
	}

	private boolean startIsBeforeLastEnd(Subtitle lastSub, Subtitle newSub) {
		return lastSub.getEnd().compareTo(newSub.getStart()) >= 0;
	}

	private boolean endIsBeforeStart(Subtitle newSub) {
		return newSub.getEnd().compareTo(newSub.getStart()) <= 0;
	}
}
