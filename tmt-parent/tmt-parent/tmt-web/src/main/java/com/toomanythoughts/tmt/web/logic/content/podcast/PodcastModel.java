package com.toomanythoughts.tmt.web.logic.content.podcast;

import java.net.URI;
import java.time.Duration;

import com.toomanythoughts.tmt.web.logic.content.ContentHeader;
import com.toomanythoughts.tmt.web.logic.content.ContentMetadata;
import com.toomanythoughts.tmt.web.logic.content.ContentModel;
import com.toomanythoughts.tmt.web.logic.content.transcript.Transcript;
import com.toomanythoughts.tmt.web.logic.content.transcript.subtitles.Subtitles;

public class PodcastModel extends ContentModel {

	public PodcastModel(ContentHeader header, ContentMetadata metadata) {
		super(header, metadata);
	}

	private URI url;
	private Subtitles subtitles;
	private Transcript transcription;
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

	public Transcript getTranscription() {
		return this.transcription;
	}

	public void setTranscription(Transcript transcription) {
		this.transcription = transcription;
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}
}
