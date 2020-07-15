package com.toomanythoughts.tmt.web.logic.security.authorization.security;

import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.logic.security.authorization.model.PermissionModel;
import com.toomanythoughts.tmt.web.persistence.entities.security.PermissionEntity;

@Service
public class PermissionService {

	public PermissionModel toModel(final PermissionEntity entity) {
		final PermissionModel model = new PermissionModel();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setConfiguration(entity.getConfiguration());
		return model;
	}

	public PermissionEntity toEntity(final PermissionModel model) {
		final PermissionEntity entity = new PermissionEntity();
		entity.setId(model.getId());
		entity.setName(model.getName());
		entity.setConfiguration(model.getConfiguration());
		return entity;
	}
}
