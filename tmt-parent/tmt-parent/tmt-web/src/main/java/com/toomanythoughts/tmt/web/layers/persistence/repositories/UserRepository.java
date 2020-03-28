package com.toomanythoughts.tmt.web.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.layers.persistence.entities.auth.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity getByUsername(String username);

	UserEntity getById(Integer id);

}
