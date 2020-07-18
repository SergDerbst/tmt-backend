package com.toomanythoughts.tmt.commons.layers.persistence;

import static com.toomanythoughts.tmt.commons.utils.reflection.JpaEntityUtils.updateEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.commons.exceptions.persistence.impl.EntityNotFoundInDatabaseRuntimeException;
import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;


public abstract class BaseCrudDao<Repo extends JpaRepository<Entity, Integer>, Entity extends BaseEntity> extends CrudDaoService<Repo, Entity, Integer> {

	@Override
	public Entity update(final Entity entity) throws EntityNotFoundInDatabaseRuntimeException {
		final Entity found = this.getRepository().getOne(entity.getId());
		if (found == null) {
			throw EntityNotFoundInDatabaseRuntimeException.prepare(entity);
		}
		return updateEntity(found, entity);
	}
}
