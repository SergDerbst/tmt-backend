package com.toomanythoughts.tmt.web.persistence.entities.security;

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
