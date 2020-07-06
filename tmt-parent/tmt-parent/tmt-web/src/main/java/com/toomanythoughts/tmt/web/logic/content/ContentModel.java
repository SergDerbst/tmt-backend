package com.toomanythoughts.tmt.web.logic.content;

import java.time.LocalDateTime;
import java.util.List;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.auth.authorization.model.UserModel;

/**
 * Abstract base class for kinds of content that can be generated by users.
 *
 * @author Sergio Weigel
 *
 */
public abstract class ContentModel extends EpicPojo {

	private ContentStatus status;
	private UserModel owner;
	private List<UserModel> authors;
	private List<UserModel> translatedBy;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime publishedAt;
	private Version version;

	public ContentStatus getStatus() {
		return this.status;
	}

	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	public UserModel getOwner() {
		return this.owner;
	}

	public void setOwner(UserModel owner) {
		this.owner = owner;
	}

	public List<UserModel> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<UserModel> authors) {
		this.authors = authors;
	}

	public List<UserModel> getTranslatedBy() {
		return this.translatedBy;
	}

	public void setTranslatedBy(List<UserModel> translatedBy) {
		this.translatedBy = translatedBy;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getPublishedAt() {
		return this.publishedAt;
	}

	public void setPublishedAt(LocalDateTime publishedAt) {
		this.publishedAt = publishedAt;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}
}
