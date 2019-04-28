package com.toomanythoughts.tmt.commons.exceptions.logic.impl;

import com.toomanythoughts.tmt.commons.exceptions.logic.LogicRuntimeException;

public class ModelEntityTransferFailedRuntimeException extends LogicRuntimeException {

	private static final long serialVersionUID = -3162457608476352903L;

	protected ModelEntityTransferFailedRuntimeException(final Throwable cause) {
		super(cause);
	}

	public static <MODEL, ENTITY> ModelEntityTransferFailedRuntimeException create(final Class<MODEL> modelClass,
																																									final Class<ENTITY> entityClass,
																																									Throwable cause) {
		final ModelEntityTransferFailedRuntimeException exception = new ModelEntityTransferFailedRuntimeException(cause);
		return (ModelEntityTransferFailedRuntimeException) exception
				.addContextValue("model class", modelClass)
				.addContextValue("entity class", entityClass);
	}
}
