package com.toomanythoughts.tmt.web.layers.logic.article.model;

import java.util.LinkedList;
import java.util.List;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.article.TextElement;

/**
 * The content represents the actual article. It consists of a main title and the article's
 * summary (or abstract) and consists of at least one chapter. Any update to its title, summary,
 * or any of its chapters' titles or structure, that is the number and order of chapters,
 * will cause a major version update of the article. Major version updates cause the article
 * to be published to the general reader.
 *
 * @author Sergio Weigel
 *
 */
public class ArticleContent extends EpicPojo {

	private String title;
	private final List<TextElement> summary = new LinkedList<>();
	private final List<ArticleChapter> chapters = new LinkedList<>();

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<TextElement> getSummary() {
		return this.summary;
	}

	public List<ArticleChapter> getChapters() {
		return this.chapters;
	}
}
