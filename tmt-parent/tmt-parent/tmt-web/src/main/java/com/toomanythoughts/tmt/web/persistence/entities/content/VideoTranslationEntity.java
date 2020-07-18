package com.toomanythoughts.tmt.web.persistence.entities.content;

import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.web.logic.content.transcript.Transcript;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "video_translations")
//@Audited
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class VideoTranslationEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "video_translation_gen", sequenceName = "video_translation_seq")
	@Column(name = "video_translation_id")
	private Integer id;

	@Column(name = "language", nullable = false)
	private Locale language;

	@Type(type = "jsonb")
	@Column(name = "transcript", nullable = false)
	private Transcript transcript;

	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false)
	private VideoEntity video;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private UserEntity owner;

	@ManyToMany(mappedBy = "contributedVideoTranslations")
	private List<UserEntity> contributors;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Locale getLanguage() {
		return this.language;
	}

	public void setLanguage(Locale language) {
		this.language = language;
	}

	public Transcript getTranscript() {
		return this.transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public UserEntity getOwner() {
		return this.owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<UserEntity> getContributors() {
		return this.contributors;
	}

	public void setContributors(List<UserEntity> contributors) {
		this.contributors = contributors;
	}

	public VideoEntity getVideo() {
		return this.video;
	}

	public void setVideo(VideoEntity video) {
		this.video = video;
	}
}
