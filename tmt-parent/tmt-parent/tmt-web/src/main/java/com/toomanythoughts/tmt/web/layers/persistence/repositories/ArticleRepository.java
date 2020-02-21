package com.toomanythoughts.tmt.web.layers.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.layers.persistence.entities.ArticleEntity;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {

}
