package com.toomanythoughts.tmt.layers.persistence.daos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.layers.persistence.entities.impl.User;
import com.toomanythoughts.tmt.layers.persistence.repository.UserRepository;

@Repository
@Transactional
public class UserDaoImpl extends BaseCrudDaoService<UserRepository, User> implements UserDao {

	@Autowired
	UserRepository repository;

	@Override
	public UserRepository getRepository() {
		return this.repository;
	}

	public User getByUsername(final String username) {
		return this.repository.getByUsername(username);
	}

	@Override
	public UserDetails loadByUsername(final String username) {
		return (UserDetails) this.repository.getByUsername(username);
	}
}
