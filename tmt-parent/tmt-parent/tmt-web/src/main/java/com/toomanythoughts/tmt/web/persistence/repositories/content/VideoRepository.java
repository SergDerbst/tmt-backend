package com.toomanythoughts.tmt.web.persistence.repositories.content;

import org.springframework.data.jpa.repository.JpaRepository;

import com.toomanythoughts.tmt.web.persistence.entities.content.VideoEntity;

public interface VideoRepository extends JpaRepository<VideoEntity, Integer> {

}
