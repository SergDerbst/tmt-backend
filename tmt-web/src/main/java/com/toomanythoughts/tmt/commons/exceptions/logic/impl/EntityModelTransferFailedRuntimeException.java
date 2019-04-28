package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class EntityModelTransferFailedRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = 8177636396656224806L;

	protected EntityModelTransferFailedRuntimeException(final Throwable cause) {
		super(cause);
	}

	public static <ENTITY, MODEL> EntityModelTransferFailedRuntimeException create(final Class<ENTITY> entityClass,
																																								 final Class<MODEL> modelClass,
																																								 final Throwable cause) {
		final EntityModelTransferFailedRuntimeException exception = new EntityModelTransferFailedRuntimeException(cause);
		return (EntityModelTransferFailedRuntimeException) exception
				.addContextValue("entity class", entityClass)
				.addContextValue("model class", modelClass);
	}
}
