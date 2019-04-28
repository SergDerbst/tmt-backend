package com.toomanythoughts.tmt.layers.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.layers.logic.model.authentication.User;
import com.toomanythoughts.tmt.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.layers.persistence.entities.impl.UserEntity;

@Service
public class UserService extends ModelService<UserEntity, User> {

	@Autowired
	UserDao userDao;

	protected UserService() {
		super(UserEntity.class, User.class);
	}

	public User getUserByUsername(String username) {
		return super.toModel(this.userDao.getByUsername(username));
	}
}
