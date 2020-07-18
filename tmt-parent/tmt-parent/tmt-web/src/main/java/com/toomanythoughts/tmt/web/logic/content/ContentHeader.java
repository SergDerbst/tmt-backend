package com.toomanythoughts.tmt.web.logic.content;

import java.time.LocalDateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.SimpleUserModel;

/**
 * Header data for any content.
 *
 * @author Sergio Weigel
 *
 */
public class ContentHeader extends EpicPojo {

	private Integer id;
	private SimpleUserModel owner;
	private LocalDateTime createdAt;
	private LocalDateTime publishedAt;
	private ContentStatus status;
	private ContentType type;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SimpleUserModel getOwner() {
		return this.owner;
	}

	public void setOwner(SimpleUserModel owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getPublishedAt() {
		return this.publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public ContentStatus getStatus() {
		return this.status;
	}

	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	public ContentType getType() {
		return this.type;
	}

	public void setType(ContentType type) {
		this.type = type;
	}
}
