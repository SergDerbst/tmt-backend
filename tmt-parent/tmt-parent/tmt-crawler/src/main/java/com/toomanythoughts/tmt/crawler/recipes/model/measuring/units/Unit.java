package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units;

import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.Ingredient;

public abstract class Unit {

	private double amount = 0.0;
	private Ingredient ingredient;

	public void amount(final double amount) {
		this.amount = amount;
	}

	public double amount() {
		return this.amount;
	}

	public void ingredient(final Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Ingredient ingredient() {
		return this.ingredient;
	}
}
