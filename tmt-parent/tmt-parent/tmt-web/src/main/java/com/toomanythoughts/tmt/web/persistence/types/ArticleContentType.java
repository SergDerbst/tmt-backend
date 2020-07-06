package com.toomanythoughts.tmt.web.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.web.logic.content.article.ArticleContent;

public class ArticleContentType extends JsonType<ArticleContent> {

	public ArticleContentType() {
		super(ArticleContent.class);
	}
}
