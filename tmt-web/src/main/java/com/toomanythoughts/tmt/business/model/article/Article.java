package com.toomanythoughts.tmt.business.model.article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.business.model.Version;

public class Article {

	private int articleId;
	private int authorId;
	private DateTime createdAt;
	private DateTime updatedAt;
	private DateTime publishedOn;
	private Version version;
	private final List<String> tags = new ArrayList<>();
	private final Map<Locale, ArticleContent> content = new HashMap<>();

	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getAuthorId() {
		return this.authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
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

	public DateTime getPublishedOn() {
		return this.publishedOn;
	}

	public void setPublishedOn(DateTime publishedOn) {
		this.publishedOn = publishedOn;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<String> getTags() {
		return this.tags;
	}

	public Map<Locale, ArticleContent> getContent() {
		return this.content;
	}
}
