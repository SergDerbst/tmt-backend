package com.toomanythoughts.tmt.web.persistence.daos;

import com.toomanythoughts.tmt.commons.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.web.persistence.entities.auth.RoleEntity;

public interface RoleDao extends CrudDao<RoleEntity, Integer> {

	RoleEntity getByName(final String name);
}
