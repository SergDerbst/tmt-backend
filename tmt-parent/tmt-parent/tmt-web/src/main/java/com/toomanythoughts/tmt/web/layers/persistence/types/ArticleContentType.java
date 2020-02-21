package com.toomanythoughts.tmt.web.layers.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.web.layers.logic.model.article.ArticleContent;

public class ArticleContentType extends JsonType<ArticleContent> {

	public ArticleContentType() {
		super(ArticleContent.class);
	}
}
