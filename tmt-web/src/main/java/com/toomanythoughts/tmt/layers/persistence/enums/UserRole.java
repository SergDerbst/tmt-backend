package com.toomanythoughts.tmt.layers.persistence.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
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

	@Override
	public String getAuthority() {
		return this.authority;
	}
}
