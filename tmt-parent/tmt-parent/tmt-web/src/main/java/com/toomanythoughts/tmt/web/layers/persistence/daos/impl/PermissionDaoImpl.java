package com.toomanythoughts.tmt.web.layers.persistence.daos.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.PermissionDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.PermissionEntity;
import com.toomanythoughts.tmt.web.layers.persistence.repositories.PermissionRepository;

@Repository
@Transactional
public class PermissionDaoImpl extends BaseCrudDaoService<PermissionRepository, PermissionEntity> implements PermissionDao {

	@Autowired
	PermissionRepository repository;

	@Override
	public PermissionRepository getRepository() {
		return this.repository;
	}

}
