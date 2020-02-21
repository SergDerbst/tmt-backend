package com.toomanythoughts.tmt.crawler.recipes.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.crawler.recipes.persistence.entities.IngredientEntity;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {

}
