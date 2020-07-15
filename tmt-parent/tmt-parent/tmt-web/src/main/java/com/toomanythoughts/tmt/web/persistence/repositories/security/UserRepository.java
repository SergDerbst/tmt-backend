package com.toomanythoughts.tmt.web.persistence.repositories.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	UserEntity getByUsername(String username);

	UserEntity getById(Integer id);

	UserEntity getByEmail(String email);

}
