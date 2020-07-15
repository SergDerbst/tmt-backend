package com.toomanythoughts.tmt.web.logic.content.video.model;

import java.net.URL;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class VideoCreationModel extends EpicPojo {

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
