package com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.metric.volume;

import com.toomanythoughts.tmt.crawler.recipes.model.measuring.units.Unit;

public class Centiliter extends Unit {

	public Centiliter(final double amount) {
		super.amount(amount);
	}

	public Liter liter() {
		return new Liter(super.amount()/100);
	}

	public Milliliter milliliter(){
		return new Milliliter(super.amount()*10);
	}
}
