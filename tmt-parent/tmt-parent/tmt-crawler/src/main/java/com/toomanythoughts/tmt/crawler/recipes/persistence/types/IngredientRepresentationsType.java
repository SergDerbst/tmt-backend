package com.toomanythoughts.tmt.crawler.recipes.persistence.types;

import com.toomanythoughts.tmt.commons.layers.persistence.types.JsonType;
import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.IngredientRepresentations;

public class IngredientRepresentationsType extends JsonType<IngredientRepresentations>{

	public IngredientRepresentationsType() {
		super(IngredientRepresentations.class);
	}
}
