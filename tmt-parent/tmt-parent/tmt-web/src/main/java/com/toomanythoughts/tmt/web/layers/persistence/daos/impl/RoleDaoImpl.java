package com.toomanythoughts.tmt.web.layers.persistence.daos.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.RoleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.RoleEntity;
import com.toomanythoughts.tmt.web.layers.persistence.repositories.RoleRepository;

@Repository
@Transactional
public class RoleDaoImpl extends BaseCrudDaoService<RoleRepository, RoleEntity> implements RoleDao {

	@Autowired
	private RoleRepository repository;

	@Override
	public RoleRepository getRepository() {
		return this.repository;
	}

	@Override
	public RoleEntity getByName(String name) {
		return this.repository.getByName(name);
	}
}
