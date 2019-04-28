package com.toomanythoughts.tmt.layers.logic.model.article;

import java.util.ArrayList;
import java.util.List;

import com.toomanythoughts.tmt.layers.logic.TextElement;
import com.toomanythoughts.tmt.layers.logic.model.EpicPojo;

public class ArticleParagraph extends EpicPojo {

	private final List<TextElement> text = new ArrayList<>();

	public List<TextElement> getText() {
		return this.text;
	}
}
