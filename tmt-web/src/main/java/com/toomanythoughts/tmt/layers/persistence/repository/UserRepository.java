package com.toomanythoughts.tmt.layers.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.layers.persistence.entities.impl.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User getByUsername(String username);

}
