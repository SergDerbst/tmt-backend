package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume;

import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Liter extends Unit {

	public Liter(final double amount) {
		super.amount(amount);
	}

	public Milliliter milliliter() {
		return new Milliliter(super.amount()*1000);
	}

	public Centiliter centiliter() {
		return new Centiliter(super.amount()*100);
	}
}
