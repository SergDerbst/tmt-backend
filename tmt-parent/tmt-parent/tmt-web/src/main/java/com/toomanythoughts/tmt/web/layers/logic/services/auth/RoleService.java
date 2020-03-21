package com.toomanythoughts.tmt.web.layers.logic.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.model.auth.Role;
import com.toomanythoughts.tmt.web.layers.logic.services.ModelService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.RoleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;

@Service
public class RoleService extends ModelService<RoleEntity, Role> {

	@Autowired
	RoleDao roleDao;

	protected RoleService() {
		super(RoleEntity.class, Role.class);
	}

	public Long countRoles() {
		return this.roleDao.count();
	}

	public void ensureRoleExists(final String name) {
		final RoleEntity roleEntity = this.roleDao.getByName(name);
		if (roleEntity == null) {
			final RoleEntity role = new RoleEntity();
			role.setName(name);
			this.roleDao.create(role);
		}
	}

	public Role getRoleByName(final String name) {
		return super.toModel(this.roleDao.getByName(name));
	}
}
