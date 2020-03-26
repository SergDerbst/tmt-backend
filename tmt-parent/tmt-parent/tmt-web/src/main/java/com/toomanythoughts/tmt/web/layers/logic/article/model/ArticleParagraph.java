package com.toomanythoughts.tmt.web.layers.logic.article.model;

import java.util.ArrayList;
import java.util.List;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;
import com.toomanythoughts.tmt.web.layers.logic.article.TextElement;

public class ArticleParagraph extends EpicPojo {

	private final List<TextElement> text = new ArrayList<>();

	public List<TextElement> getText() {
		return this.text;
	}
}
