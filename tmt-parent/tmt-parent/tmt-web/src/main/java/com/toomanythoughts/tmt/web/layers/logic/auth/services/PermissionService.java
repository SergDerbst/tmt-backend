package com.toomanythoughts.tmt.web.layers.logic.auth.services;

import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.StandardModelService;
import com.toomanythoughts.tmt.web.layers.logic.auth.model.Permission;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;

@Service
public class PermissionService extends StandardModelService<Permission, PermissionEntity> {

	@Override
	public Permission toModel(final PermissionEntity entity) {
		final Permission model = new Permission();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setConfiguration(entity.getConfiguration());
		return model;
	}

	@Override
	public PermissionEntity toEntity(final Permission model) {
		final PermissionEntity entity = new PermissionEntity();
		entity.setId(model.getId());
		entity.setName(entity.getName());
		entity.setConfiguration(model.getConfiguration());
		return entity;
	}
}
