package com.toomanythoughts.tmt.web.logic.content.video.model;

import java.time.Duration;

import com.toomanythoughts.tmt.web.logic.content.ContentMetadata;

public class VideoMetadata extends ContentMetadata {

	private Duration duration;

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
