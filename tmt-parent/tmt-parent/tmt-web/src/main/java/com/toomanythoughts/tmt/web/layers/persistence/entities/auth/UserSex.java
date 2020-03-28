package com.toomanythoughts.tmt.web.layers.persistence.entities.auth;

public enum UserSex {
	Male("male"),
	Female("female"),
	Other("other");

	private String representation;

	private UserSex(final String representation) {
		this.representation = representation;
	}

	public String getRepresentation() {
		return this.representation;
	}
}
