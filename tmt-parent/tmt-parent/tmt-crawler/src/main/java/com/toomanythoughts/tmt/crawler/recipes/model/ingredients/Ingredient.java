package com.toomanythoughts.tmt.crawler.recipes.model.ingredients;

import java.util.List;

public class Ingredient {

	private Integer id;
	private String key;
	private List<String> representations;
	private StateOfMatter stateOfMatter;
	private double cupToGramRatio;
	private double stickToGramRatio;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<String> getRepresentations() {
		return this.representations;
	}

	public void setRepresentations(List<String> representations) {
		this.representations = representations;
	}

	public StateOfMatter getStateOfMatter() {
		return this.stateOfMatter;
	}

	public void setStateOfMatter(StateOfMatter stateOfMatter) {
		this.stateOfMatter = stateOfMatter;
	}

	public double cupToGramRatio() {
		return this.cupToGramRatio;
	}

	public void setCupToGramRatio(double cupToGramRatio) {
		this.cupToGramRatio = cupToGramRatio;
	}

	public double getStickToGramRation() {
		return this.stickToGramRatio;
	}

	public void setStickToGramRation(double stickToGramRation) {
		this.stickToGramRatio = stickToGramRation;
	}
}
