package com.toomanythoughts.tmt.web.layers.logic;

/**
 * Service to pass data between model and entity. If the model structure
 * changes between request and response, the implemented {@link ModifyingModelService}
 * can be implemented directly.
 *
 * @author Sergio Weigel
 *
 * @param <MODEL>
 * @param <ENTITY>
 */
public abstract class StandardModelService<MODEL, ENTITY> extends ModifyingModelService<MODEL, MODEL, ENTITY> {

}
