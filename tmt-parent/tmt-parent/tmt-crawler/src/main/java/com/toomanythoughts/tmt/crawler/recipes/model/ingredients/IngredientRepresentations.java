package com.toomanythoughts.tmt.crawler.recipes.model.ingredients;

import java.util.EnumMap;
import java.util.List;

import com.neovisionaries.i18n.LanguageCode;
import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

public class IngredientRepresentations extends EpicPojo {

	private final EnumMap<LanguageCode, List<String>> representations = new EnumMap<>(LanguageCode.class);

	public EnumMap<LanguageCode, List<String>> getRepresentations() {
		return representations;
	}
}
