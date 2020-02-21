package com.toomanythoughts.tmt.crawler.recipes.persistence.daos.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.crawler.recipes.persistence.entities.IngredientEntity;
import com.toomanythoughts.tmt.crawler.recipes.persistence.repositories.IngredientRepository;

@Repository
@Transactional
public class IngredientDaoImpl extends BaseCrudDaoService<IngredientRepository, IngredientEntity>{

	@Autowired
	IngredientRepository repository;

	@Override
	public IngredientRepository getRepository() {
		return this.repository;
	}
}
