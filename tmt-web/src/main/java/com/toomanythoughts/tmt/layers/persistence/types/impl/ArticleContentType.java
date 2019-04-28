package com.toomanythoughts.tmt.layers.persistence.types.impl;

import com.toomanythoughts.tmt.layers.logic.model.article.ArticleContent;
import com.toomanythoughts.tmt.layers.persistence.types.JsonType;

public class ArticleContentType extends JsonType<ArticleContent> {

	public ArticleContentType() {
		super(ArticleContent.class);
	}
}
