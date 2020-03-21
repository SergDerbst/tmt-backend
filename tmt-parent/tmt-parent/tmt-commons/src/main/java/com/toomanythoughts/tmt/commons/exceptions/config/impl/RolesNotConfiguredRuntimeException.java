package com.toomanythoughts.tmt.commons.exceptions.config.impl;

import com.toomanythoughts.tmt.commons.exceptions.config.ConfigRuntimeException;

public class RolesNotConfiguredRuntimeException extends ConfigRuntimeException {

	private static final long serialVersionUID = 4535530127562536562L;

	public static RolesNotConfiguredRuntimeException prepare(final Integer numberOfRoles) {
		return (RolesNotConfiguredRuntimeException) new RolesNotConfiguredRuntimeException()
				.addContextValue("expected number of roles", numberOfRoles);
	}
}
