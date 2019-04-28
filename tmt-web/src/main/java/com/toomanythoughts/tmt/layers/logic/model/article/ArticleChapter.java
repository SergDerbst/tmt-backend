package com.toomanythoughts.tmt.layers.logic.model.article;

import java.util.LinkedList;
import java.util.List;

import com.toomanythoughts.tmt.layers.logic.model.EpicPojo;

/**
 * A chapter represents the top-level structure of an article. It should be represented as
 * paginated and is itself structured in sections. Any change of a chapter's title or its
 * sections' structure will cause a minor version update. Minor version updates are only
 * published to an author's followers.
 *
 * a minor version update
 *
 * @author Sergio Weigel
 *
 */
public class ArticleChapter extends EpicPojo {

	private String title;
	private final List<ArticleSection> sections = new LinkedList<>();

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ArticleSection> getSections() {
		return this.sections;
	}
}
