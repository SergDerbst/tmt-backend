package com.toomanythoughts.tmt.web.logic.content.article;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.logic.content.ContentVersion;
import com.toomanythoughts.tmt.web.logic.security.authorization.model.SimpleUserModel;

public class ArticleModel extends EpicPojo {

	private Integer articleId;
	private SimpleUserModel owner;
	private List<SimpleUserModel> authors;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime published;
	private ContentVersion version;

	private final Map<Locale, ArticleContent> content = new HashMap<>();

	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
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

	public LocalDateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getPublished() {
		return this.published;
	}

	public void setPublished(LocalDateTime published) {
		this.published = published;
	}

	public ContentVersion getVersion() {
		return this.version;
	}

	public void setVersion(ContentVersion version) {
		this.version = version;
	}

	public List<SimpleUserModel> getAuthors() {
		return this.authors;
	}

	public void setAuthors(final List<SimpleUserModel> authors) {
		this.authors = authors;
	}

	public Map<Locale, ArticleContent> getContent() {
		return this.content;
	}
}
