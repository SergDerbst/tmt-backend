package com.toomanythoughts.tmt.web.layers.persistence.entities.auth;

public enum UserTitle {
	MaleTitle ("male"),
	FemaleTitle("female");

	private String representation;

	private UserTitle(final String representation) {
		this.representation = representation;
	}

	public String getRepresentation() {
		return this.representation;
	}
}
