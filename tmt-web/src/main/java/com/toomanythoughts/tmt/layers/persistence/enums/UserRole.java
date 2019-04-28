package com.toomanythoughts.tmt.layers.persistence.enums;

public enum UserRole {
	Reader("Reader"),
	Voter("Voter"),
	Author("Author"),
	Moderator("Moderator"),
	Admin("Admin"),
	God("God");

	private String authority;

	private UserRole(final String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}
}
