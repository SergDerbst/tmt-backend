package com.toomanythoughts.tmt.web.persistence.daos.content.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.toomanythoughts.tmt.commons.layers.persistence.BaseCrudDao;
import com.toomanythoughts.tmt.web.persistence.daos.content.VideoDao;
import com.toomanythoughts.tmt.web.persistence.entities.content.VideoEntity;
import com.toomanythoughts.tmt.web.persistence.repositories.content.VideoRepository;

@Repository
@Transactional
public class VideoDaoImpl extends BaseCrudDao<VideoRepository, VideoEntity> implements VideoDao {

	@Autowired
	VideoRepository repository;

	@Override
	public VideoRepository getRepository() {
		return this.repository;
	}
}
