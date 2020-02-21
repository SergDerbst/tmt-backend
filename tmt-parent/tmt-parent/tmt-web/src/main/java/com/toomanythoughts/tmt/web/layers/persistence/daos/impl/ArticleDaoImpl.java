package com.toomanythoughts.tmt.web.layers.persistence.daos.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDaoService;
import com.toomanythoughts.tmt.web.layers.persistence.daos.ArticleDao;
import com.toomanythoughts.tmt.web.layers.persistence.entities.ArticleEntity;
import com.toomanythoughts.tmt.web.layers.persistence.repositories.ArticleRepository;

@Repository
@Transactional
public class ArticleDaoImpl extends BaseCrudDaoService<ArticleRepository, ArticleEntity> implements ArticleDao {

	@Autowired
	ArticleRepository repository;

	@Override
	public ArticleRepository getRepository() {
		return this.repository;
	}

}
