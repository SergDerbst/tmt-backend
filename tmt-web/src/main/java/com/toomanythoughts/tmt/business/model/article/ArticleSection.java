package com.toomanythoughts.tmt.business.model.article;

import java.util.LinkedList;
import java.util.List;

/**
 * A section represents a set of paragraphs with a header as subheading within a chapter.
 * It serves to further structure an article's chapter. Any change in its header or structure
 * of paragraphs, that is their number or order, will cause a minor version update of the
 * article. A minor version update will only be published to followers.
 *
 * @author Sergio Weigel
 *
 */
public class ArticleSection {

	private final List<ArticleParagraph> paragraphs = new LinkedList<>();
	private String subheading;

	public ArticleSection(final String header) {
		this.subheading = header;
	}

	public List<ArticleParagraph> getParagraphs() {
		return this.paragraphs;
	}

	public String getSubheading() {
		return this.subheading;
	}

	public void setSubheading(String header) {
		this.subheading = header;
	}
}
