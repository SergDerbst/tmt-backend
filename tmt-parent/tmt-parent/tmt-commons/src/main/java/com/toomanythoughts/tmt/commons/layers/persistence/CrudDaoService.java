package com.toomanythoughts.tmt.commons.layers.persistence;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.commons.exceptions.persistence.impl.EntityNotFoundInDatabaseRuntimeException;

/**
 * Abstract base implementation of {@link CrudDao}.
 *
 * @param <Entity>
 *          the entity to work on.
 * @param <ID>
 *          the primary key.
 * @param <Repo>
 *          the repository for the entity.
 * @author Sergio Weigel
 */
@Transactional(rollbackFor = EntityNotFoundInDatabaseRuntimeException.class)
public abstract class CrudDaoService<Repo extends JpaRepository<Entity, ID>, Entity, ID extends Serializable> implements CrudDao<Entity, ID> {

	public abstract Repo getRepository();

	@Override
	public abstract Entity update(final Entity entity) throws EntityNotFoundInDatabaseRuntimeException;

	@Override
	public Entity readById(final ID id) {
		return this.getRepository()
								.getOne(id);
	}

	@Override
	public List<Entity> readAll() {
		return this.getRepository()
								.findAll();
	}

	@Override
	public Entity create(final Entity entity) {
		return this.getRepository()
								.save(entity);
	}

	@Override
	public List<Entity> createAll(final List<Entity> entities) {
		return this.getRepository()
								.saveAll(entities);
	}

	@Override
	public void delete(final ID id) {
		this.getRepository()
				.deleteById(id);
	}

	@Override
	public boolean exists(final ID id) {
		return this.getRepository()
								.existsById(id);
	}

	@Override
	public long count() {
		return this.getRepository()
								.count();
	}
}
