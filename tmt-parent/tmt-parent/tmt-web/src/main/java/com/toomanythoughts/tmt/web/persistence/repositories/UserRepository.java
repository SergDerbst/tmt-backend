package com.toomanythoughts.tmt.web.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.persistence.entities.auth.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity getByUsername(String username);

	UserEntity getById(Integer id);

	UserEntity getByEmail(String email);

}
