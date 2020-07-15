package com.toomanythoughts.tmt.web.logic.content.video.model;

import java.util.Map;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

/**
 * DTO representing one unit of subtitles.
 *
 * @author Sergio Weigel
 *
 */
public class Subtitle extends EpicPojo implements Comparable<Subtitle> {

	private Long start;
	private Long end;
	private String text;
	private final Metadata metadata = new Metadata();

	public Long getStart() {
		return this.start;
	}

	public void setStart(Long start) {
		this.start = start;
	}

	public Long getEnd() {
		return this.end;
	}

	public void setEnd(Long end) {
		this.end = end;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Map<String, String> getMetadata() {
		return this.metadata;
	}

	@Override
	public int compareTo(Subtitle other) {
		int compare = this.start.compareTo(other.start);
		if (compare != 0) {
			return compare;
		}
		compare = this.end.compareTo(other.end);
		if (compare != 0) {
			return compare;
		}
		compare = this.text.compareTo(other.text);
		if (compare != 0) {
			return compare;
		}
		return this.metadata.compareTo(other.metadata);
	}
}
