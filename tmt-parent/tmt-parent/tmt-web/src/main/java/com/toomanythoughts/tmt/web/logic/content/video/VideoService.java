package com.toomanythoughts.tmt.web.logic.content.video;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.toomanythoughts.tmt.commons.exceptions.logic.impl.FormDataInvalidException;
import com.toomanythoughts.tmt.web.exceptions.content.ContentNotFoundException;
import com.toomanythoughts.tmt.web.logic.content.ContentStatus;
import com.toomanythoughts.tmt.web.logic.content.ContentType;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoCreationModel;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoMetadata;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoModel;
import com.toomanythoughts.tmt.web.logic.security.CurrentUserService;
import com.toomanythoughts.tmt.web.logic.security.authorization.security.UserService;
import com.toomanythoughts.tmt.web.persistence.daos.content.VideoDao;
import com.toomanythoughts.tmt.web.persistence.entities.content.VideoEntity;

@Service
public class VideoService {

	@Autowired
	private VideoDao videoDao;
	@Autowired
	private UserService userService;
	@Autowired
	private CurrentUserService currentUserService;

	/**
	 * Creates a new video.
	 *
	 * @param creationModel
	 * @return
	 * @throws FormDataInvalidException
	 */
	public VideoModel create(final VideoCreationModel creationModel) throws FormDataInvalidException {
		return this.toModel(this.videoDao.create(this.createEntity(creationModel)));
	}

	/**
	 * Retrieves a video by the given id.
	 *
	 * @param id
	 * @return
	 * @throws FormDataInvalidException
	 */
	public VideoModel get(Integer id) throws FormDataInvalidException {
		final VideoEntity entity = this.videoDao.readById(id);
		return this.toModel(entity);
	}

	/**
	 * Updated an existing model, if it exists.
	 *
	 * @param model
	 * @return
	 * @throws ContentNotFoundException
	 * @throws FormDataInvalidException
	 */
	public VideoModel update(VideoModel model) throws ContentNotFoundException, FormDataInvalidException {
		final VideoEntity entity = this.videoDao.readById(model.getHeader().getId());
		if (entity == null) {
			throw ContentNotFoundException.prepare("video not found", ContentType.Video, model.getHeader().getId(), model.getHeader().getTitle());
		}
		return this.toModel(this.updateEntity(entity, model));
	}

	private VideoEntity updateEntity(VideoEntity entity, VideoModel model) {
		entity.setDuration(model.getMetadata().getDuration());
		entity.setId(model.getHeader().getId());
		entity.setMetadata(model.getMetadata());
		entity.setOwner(this.userService.entityById(model.getHeader().getOwner().getId()));
		entity.setStatus(model.getHeader().getStatus());
		entity.setTitle(model.getHeader().getTitle());
		entity.setTranscript(model.getTranscript());
		entity.setUrl(model.getHeader().getUrl().toString());
		return this.videoDao.update(entity);
	}

	private VideoModel toModel(VideoEntity entity) throws FormDataInvalidException {
		try {
			final VideoModel videoModel = new VideoModel();
			videoModel.getHeader().setCreatedAt(new Timestamp(entity.getCreatedAt().getTime()).toLocalDateTime());
			videoModel.getHeader().setId(entity.getId());
			videoModel.setMetadata(entity.getMetadata());
			videoModel.getHeader().setOwner(this.userService.toSimpleModel(entity.getOwner()));
			videoModel.getHeader().setStatus(entity.getStatus());
			videoModel.setTranscript(entity.getTranscript());
			videoModel.getHeader().setTitle(entity.getTitle());
			videoModel.getHeader().setUrl(new URL(entity.getUrl()));
			return videoModel;
		} catch (final MalformedURLException e) {
			throw FormDataInvalidException.prepare("invalid video url", "url", entity.getUrl());
		}
	}

	private VideoEntity createEntity(VideoCreationModel creationModel) {
		final VideoEntity videoEntity = new VideoEntity();
		videoEntity.setCreatedAt(new Date());
		videoEntity.setMetadata(new VideoMetadata());
		videoEntity.setOwner(this.currentUserService.entity());
		videoEntity.setStatus(ContentStatus.Created);
		videoEntity.setTitle(creationModel.getTitle());
		videoEntity.setUrl(creationModel.getUrl().toString());
		return videoEntity;
	}
}
