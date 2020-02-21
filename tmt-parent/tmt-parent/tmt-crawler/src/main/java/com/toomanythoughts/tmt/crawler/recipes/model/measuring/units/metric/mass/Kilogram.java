package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.mass;

import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.Ingredient;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Kilogram extends Unit {

	public Kilogram(final double amount) {
		super.amount(amount);
	}

	public Kilogram(final Ingredient ingredient, final double amount) {
		super.amount(amount);
		super.ingredient(ingredient);
	}

	public Gram gram() {
		return new Gram(super.ingredient(), super.amount()*1000.0);
	}

	public Milligram milligram() {
		return new Milligram(super.ingredient(), super.amount()*1000000.0);
	}

	public Microgram microgram() {
		return new Microgram(super.ingredient(), super.amount()*1000000000.0);
	}
}
