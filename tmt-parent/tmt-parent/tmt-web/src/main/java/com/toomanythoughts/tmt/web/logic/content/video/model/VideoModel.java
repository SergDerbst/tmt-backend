package com.toomanythoughts.tmt.web.logic.content.video.model;

import java.net.URI;
import java.time.Duration;

import com.toomanythoughts.tmt.web.logic.content.ContentModel;
import com.toomanythoughts.tmt.web.logic.content.transcript.Transcription;

public class VideoModel extends ContentModel {

	private URI url;
	private Subtitles subtitles;
	private Transcription transcription;
	private Duration duration;

	public URI getUrl() {
		return this.url;
	}

	public void setUrl(URI url) {
		this.url = url;
	}

	public Subtitles getSubtitles() {
		return this.subtitles;
	}

	public void setSubtitles(Subtitles subtitles) {
		this.subtitles = subtitles;
	}

	public Transcription getTranscription() {
		return this.transcription;
	}

	public void setTranscription(Transcription transcription) {
		this.transcription = transcription;
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
