package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.imperial;

import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.Ingredient;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.mass.Gram;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume.Centiliter;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume.Liter;
import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume.Milliliter;

public final class Cup extends Unit {

	public static final double Cups_To_Milliliter_Ratio = 236.588;

	public Cup(final double amount) {
		super.amount(amount);
	}

	public Cup(final Ingredient ingredient, final double amount) {
		super.amount(amount);
		super.ingredient(ingredient);
	}

	public static Cup fromCentiliter(final Centiliter centiliter) {
		return fromMilliliter(centiliter.milliliter());
	}

	public static Cup fromLiter(final Liter liter) {
		return fromMilliliter(liter.milliliter());
	}

	public static Cup fromMilliliter(final Milliliter milliliter) {
		return new Cup(milliliter.amount()/Cups_To_Milliliter_Ratio);
	}

	public Centiliter centiliter() {
		return this.milliliter().centiliter();
	}

	public Liter liter() {
		return this.milliliter().liter();
	}

	public Milliliter milliliter() {
		return new Milliliter(super.amount()*Cups_To_Milliliter_Ratio);
	}

	public Cup fromGram(final Gram gram) {
		return new Cup(gram.amount()/super.ingredient().cupToGramRatio());
	}

	public Gram toGram() {
		return new Gram(super.ingredient(), super.amount()*super.ingredient().cupToGramRatio());
	}
}
