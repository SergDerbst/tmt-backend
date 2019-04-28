package com.toomanythoughts.tmt.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.layers.persistence.entities.impl.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

}
