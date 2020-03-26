package com.toomanythoughts.tmt.web.layers.logic;

/**
 * Abstract base class for all model entity services that modify the model. There is
 * a request model and a response model. This is typically true for a model that goes
 * through different stages in its lifecycle, such as a user model for registration, email
 * validation, role upgrade etc.
 *
 * @author Sergio Weigel
 *
 * @param <REQUEST_MODEL>
 * @param <RESPONSE_MODEL>
 * @param <ENTITY>
 */
public abstract class ModifyingModelService <REQUEST_MODEL, RESPONSE_MODEL, ENTITY> {

	public abstract RESPONSE_MODEL toModel(final ENTITY entity);

	public abstract ENTITY toEntity(final REQUEST_MODEL model);
}
