package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.mass;

import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.Ingredient;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Milligram extends Unit {

	public Milligram(final double amount) {
		super.amount(amount);
	}

	public Milligram(final Ingredient ingredient, final double amount) {
		super.amount(amount);
		super.ingredient(ingredient);
	}

	public Microgram microgram() {
		return new Microgram(super.ingredient(), super.amount()*1000.0);
	}

	public Gram gram() {
		return new Gram(super.ingredient(), super.amount()/1000.0);
	}

	public Kilogram kilogram() {
		return new Kilogram(super.ingredient(), super.amount()/1000000.0);
	}
}
