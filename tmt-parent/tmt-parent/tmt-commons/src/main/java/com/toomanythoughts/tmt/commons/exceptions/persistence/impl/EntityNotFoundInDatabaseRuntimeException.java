package com.toomanythoughts.tmt.commons.exceptions.persistence.impl;

import com.toomanythoughts.tmt.commons.exceptions.persistence.PersistenceRuntimeException;

@SuppressWarnings("serial")
public class EntityNotFoundInDatabaseRuntimeException extends PersistenceRuntimeException {

	public static EntityNotFoundInDatabaseRuntimeException prepare(final Object entity) {
		return (EntityNotFoundInDatabaseRuntimeException) new EntityNotFoundInDatabaseRuntimeException().addContextValue("entity", entity);
	}
}