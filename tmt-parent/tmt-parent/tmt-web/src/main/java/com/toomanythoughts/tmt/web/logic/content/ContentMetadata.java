package com.toomanythoughts.tmt.web.logic.content;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

/**
 * Metadata for any content.
 *
 * @author Sergio Weigel
 *
 */
public class ContentMetadata extends EpicPojo {

	private String description;

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
