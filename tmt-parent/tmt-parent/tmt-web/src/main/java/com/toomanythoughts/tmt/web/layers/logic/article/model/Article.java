package com.toomanythoughts.tmt.web.layers.logic.article.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.joda.time.DateTime;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.article.Version;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.user.UserForRegistrationModel;

public class Article extends EpicPojo {

	private Integer articleId;
	private UserForRegistrationModel owner;
	private DateTime createdAt;
	private DateTime updatedAt;
	private DateTime published;
	private Version version;

	private final List<UserForRegistrationModel> authors = new ArrayList<>();
	private final Map<Locale, ArticleContent> content = new HashMap<>();

	public int getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public UserForRegistrationModel getOwner() {
		return this.owner;
	}

	public void setOwner(UserForRegistrationModel owner) {
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

	public List<UserForRegistrationModel> getAuthors() {
		return this.authors;
	}

	public Map<Locale, ArticleContent> getContent() {
		return this.content;
	}
}
