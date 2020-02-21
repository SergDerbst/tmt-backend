package com.toomanythoughts.tmt.web.layers.logic.model.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.Version;
import com.toomanythoughts.tmt.web.layers.logic.model.authentication.User;

public class Article extends EpicPojo {

	private Integer articleId;
	private User owner;
	private DateTime createdAt;
	private DateTime updatedAt;
	private DateTime published;
	private Version version;

	private final List<User> authors = new ArrayList<>();
	private final Map<Locale, ArticleContent> content = new HashMap<>();

	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public User getOwner() {
		return this.owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public DateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(DateTime createdAt) {
		this.createdAt = createdAt;
	}

	public DateTime getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(DateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public DateTime getPublished() {
		return this.published;
	}

	public void setPublished(DateTime published) {
		this.published = published;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<User> getAuthors() {
		return this.authors;
	}

	public Map<Locale, ArticleContent> getContent() {
		return this.content;
	}
}
