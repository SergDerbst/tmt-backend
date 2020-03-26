package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.StandardModelService;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.Permission;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.Role;
import com.toomanythoughts.tmt.web.layers.persistence.daos.RoleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;

@Service
public class RoleService extends StandardModelService<Role, RoleEntity> {

	@Autowired
	RoleDao roleDao;
	@Autowired
	PermissionService permissionService;

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

	public Role findByName(String name) {
		final RoleEntity roleEntity = this.roleDao.getByName(name);
		if (roleEntity != null) {
			return this.toModel(roleEntity);
		} else {
			return null;
		}
	}

	public Role findDefaultRole() {
		return this.findByName("Reader");
	}

	@Override
	public Role toModel(final RoleEntity entity) {
		final Role model = new Role();
		model.setId(entity.getId());
		model.setName(entity.getName());
		final Set<Permission> permissions = new HashSet<>();
		if (entity.getPermissions() != null && !entity.getPermissions().isEmpty()) {
			for (final PermissionEntity permissionEntity : entity.getPermissions()) {
				permissions.add(this.permissionService.toModel(permissionEntity));
			}
		}
		model.setPermissions(permissions);
		return model;
	}

	@Override
	public RoleEntity toEntity(final Role model) {
		final RoleEntity entity = new RoleEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		final Set<PermissionEntity> permissions = new HashSet<>();
		if (model.getPermissions() != null && !model.getPermissions().isEmpty()) {
			for (final Permission permission : model.getPermissions()) {
				permissions.add(this.permissionService.toEntity(permission));
			}
		}
		entity.setPermissions(permissions);
		return entity;
	}
}
