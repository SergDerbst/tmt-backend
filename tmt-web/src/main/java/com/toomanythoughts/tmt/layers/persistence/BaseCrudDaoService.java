package com.toomanythoughts.tmt.layers.persistence;

import static com.toomanythoughts.tmt.commons.utils.reflection.JpaEntityUtils.updateEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.commons.exceptions.persistence.impl.EntityNotFoundInDatabaseException;
import com.toomanythoughts.tmt.layers.persistence.entities.BaseEntity;


public abstract class BaseCrudDaoService<Repo extends JpaRepository<Entity, Integer>, Entity extends BaseEntity> extends CrudDaoService<Repo, Entity, Integer> {

	@Override
	public Entity update(final Entity entity) throws EntityNotFoundInDatabaseException {
		final Entity found = this.getRepository().getOne(entity.getId());
		if (found == null) {
			throw EntityNotFoundInDatabaseException.prepare(entity);
		}
		return updateEntity(found, entity);
	}
}
