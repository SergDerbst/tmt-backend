package com.toomanythoughts.tmt.web.logic.content.video.model;

import java.net.URL;

import com.toomanythoughts.tmt.web.logic.content.ContentHeader;

public class VideoHeader extends ContentHeader {

	private String title;
	private URL url;

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public URL getUrl() {
		return this.url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}
}
