package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.mass;

import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.Ingredient;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Microgram extends Unit {

	public Microgram(final double amount) {
		super.amount(amount);
	}

	public Microgram(final Ingredient ingredient, final double amount) {
		super.amount(amount);
		super.ingredient(ingredient);
	}

	public Milligram milligram() {
		return new Milligram(super.ingredient(), super.amount()/1000.0);
	}

	public Gram gram() {
		return new Gram(super.ingredient(), super.amount()/1000000.0);
	}

	public Kilogram kilogram() {
		return new Kilogram(super.ingredient(), super.amount()/1000000000.0);
	}
}
