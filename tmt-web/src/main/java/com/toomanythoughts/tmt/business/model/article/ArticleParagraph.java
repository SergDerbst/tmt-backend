package com.toomanythoughts.tmt.business.model.article;

import java.util.ArrayList;
import java.util.List;

import com.toomanythoughts.tmt.business.model.TextElement;

public class ArticleParagraph {

	private final List<TextElement> text = new ArrayList<>();

	public List<TextElement> getText() {
		return text;
	}
}
