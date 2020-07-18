package com.toomanythoughts.tmt.web.logic.content.video.model;

import com.toomanythoughts.tmt.web.logic.content.ContentModel;
import com.toomanythoughts.tmt.web.logic.content.transcript.Transcript;

public class VideoModel extends ContentModel<VideoHeader, VideoMetadata> {

	public VideoModel() {
		this(new VideoHeader(), new VideoMetadata());
	}

	public VideoModel(final VideoHeader header, final VideoMetadata metadata) {
		super(header, metadata);
	}

	private Transcript transcript;

	public Transcript getTranscript() {
		return this.transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}
}
