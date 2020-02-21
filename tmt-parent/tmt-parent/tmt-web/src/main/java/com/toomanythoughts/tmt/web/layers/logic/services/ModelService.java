package com.toomanythoughts.tmt.web.layers.logic.services;

import java.lang.reflect.Field;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.EntityModelTransferFailedRuntimeException;
import com.toomanythoughts.tmt.commons.exceptions.logic.impl.ModelEntityTransferFailedRuntimeException;
import com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils;

/**
 * Basic model services that offers to transfer data between entity and model
 * using reflection.
 *
 * <i>Note:</i> Property fields of both model and entity must be present on both
 * objects and have the same names for transfers to work. It's intended to work
 * for most regular cases, otherwise overwrite the transfer methods as you see fit.
 *
 * @author Sergio Weigel
 *
 * @param <ENTITY>
 * @param <MODEL>
 */
public abstract class ModelService<ENTITY, MODEL> {

	private final Class<ENTITY> entityClass;
	private final Class<MODEL> modelClass;

	protected ModelService(final Class<ENTITY> entityClass, final Class<MODEL> modelClass) {
		this.entityClass = entityClass;
		this.modelClass = modelClass;
	}

	protected MODEL toModel(final ENTITY entity) {
		try {
			return this.transferProperties(this.modelClass, entity);
		} catch (final Exception e) {
			throw EntityModelTransferFailedRuntimeException.create(this.entityClass, this.modelClass, e);
		}
	}

	protected ENTITY toEntity(final MODEL model) {
		try {
			return this.transferProperties(this.entityClass, model);
		} catch (final Exception e) {
			throw ModelEntityTransferFailedRuntimeException.create(this.modelClass, this.entityClass, e);
		}
	}

	private <TARGET, SOURCE> TARGET transferProperties(final Class<TARGET> targetClass,
																											final SOURCE source) throws Exception {
			final TARGET target = targetClass.getDeclaredConstructor().newInstance();
			for (final Field field : ClassReflectionUtils.retrievePropertyFields(source.getClass())) {
				field.setAccessible(true);
				field.set(target, field.get(source));
			}
			return target;
	}
}
