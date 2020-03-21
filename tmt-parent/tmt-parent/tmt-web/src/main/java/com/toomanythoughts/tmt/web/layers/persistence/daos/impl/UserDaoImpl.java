package com.toomanythoughts.tmt.web.layers.persistence.daos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;
import com.toomanythoughts.tmt.web.layers.persistence.repositories.UserRepository;

@Repository
@Transactional
public class UserDaoImpl extends BaseCrudDaoService<UserRepository, UserEntity> implements UserDao {

	@Autowired
	UserRepository repository;

	@Override
	public UserRepository getRepository() {
		return this.repository;
	}

	@Override
	public UserEntity getByUsername(final String username) {
		return this.repository.getByUsername(username);
	}
}
