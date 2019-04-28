package com.toomanythoughts.tmt.layers.logic.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.layers.logic.model.authentication.UserModel;
import com.toomanythoughts.tmt.layers.persistence.daos.UserDao;
import com.toomanythoughts.tmt.layers.persistence.entities.impl.User;

@Service
public class UserService extends ModelService<User, UserModel> implements UserDetailsService {

	@Autowired
	UserDao userDao;

	protected UserService() {
		super(User.class, UserModel.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return this.userDao.loadByUsername(username);
	}


}
