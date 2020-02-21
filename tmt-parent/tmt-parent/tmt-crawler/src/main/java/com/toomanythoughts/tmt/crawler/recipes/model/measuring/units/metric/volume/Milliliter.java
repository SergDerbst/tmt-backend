package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume;

import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Milliliter extends Unit {

	public Milliliter(final double amount) {
		super.amount(amount);
	}

	public Liter liter() {
		return new Liter(super.amount()/1000);
	}

	public Centiliter centiliter() {
		return new Centiliter(super.amount()/100);
	}
}
