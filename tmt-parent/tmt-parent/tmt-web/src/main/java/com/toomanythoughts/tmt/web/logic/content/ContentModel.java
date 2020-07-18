package com.toomanythoughts.tmt.web.logic.content;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

/**
 * Abstract base class for kinds of content that can be generated by users.
 *
 * @author Sergio Weigel
 *
 */
public abstract class ContentModel<Header extends ContentHeader, Metadata extends ContentMetadata> extends EpicPojo {

	private Header header;
	private Metadata metadata;

	public ContentModel(final Header header, final Metadata metadata) {
		this.header = header;
		this.metadata = metadata;
	}

	public Header getHeader() {
		return this.header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public Metadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
}
