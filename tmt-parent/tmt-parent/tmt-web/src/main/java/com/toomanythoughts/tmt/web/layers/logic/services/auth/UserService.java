package com.toomanythoughts.tmt.web.layers.logic.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.web.layers.logic.model.auth.User;
import com.toomanythoughts.tmt.web.layers.logic.services.ModelService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

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
