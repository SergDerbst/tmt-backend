package com.toomanythoughts.tmt.web.persistence.daos.security;

import com.toomanythoughts.tmt.commons.layers.persistence.CrudDao;
import com.toomanythoughts.tmt.web.persistence.entities.security.RoleEntity;

public interface RoleDao extends CrudDao<RoleEntity, Integer> {

	RoleEntity getByName(final String name);
}
