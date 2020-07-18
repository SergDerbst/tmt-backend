package com.toomanythoughts.tmt.web.persistence.entities.content;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.web.logic.content.ContentStatus;
import com.toomanythoughts.tmt.web.logic.content.transcript.Transcript;
import com.toomanythoughts.tmt.web.logic.content.video.model.VideoMetadata;
import com.toomanythoughts.tmt.web.persistence.entities.security.UserEntity;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity
@Table(name = "videos")
//@Audited
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class VideoEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "video_gen", sequenceName = "video_seq")
	@Column(name =  "video_id")
	private Integer id;

	@Column(name = "title", nullable = false, length = 70)
	private String title;

	@Column(name = "url", nullable = false, unique = true)
	private String url;

	@Column(name = "duration")
	private Duration duration;

	@Type(type = "jsonb")
	@Column(name = "metadata")
	private VideoMetadata metadata;

	@Column(name = "created_at")
	private Date createdAt;

	@ManyToOne
	@JoinColumn(name = "owner_id", nullable = false)
	private UserEntity owner;

	@ManyToOne
	@JoinColumn(name = "transcriber_id")
	private UserEntity transcriber;

	@Type(type = "jsonb")
	@Column(name = "transcript")
	private Transcript transcript;

	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<VideoTranslationEntity> translations;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	@Type(type ="com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType")
	private ContentStatus status;
	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public UserEntity getOwner() {
		return this.owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public Duration getDuration() {
		return this.duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public VideoMetadata getMetadata() {
		return this.metadata;
	}

	public void setMetadata(VideoMetadata metadata) {
		this.metadata = metadata;
	}

	public Transcript getTranscript() {
		return this.transcript;
	}

	public void setTranscript(Transcript transcript) {
		this.transcript = transcript;
	}

	public UserEntity getTranscriber() {
		return this.transcriber;
	}

	public void setTranscriber(UserEntity transcriber) {
		this.transcriber = transcriber;
	}

	public List<VideoTranslationEntity> getTranslations() {
		return this.translations;
	}

	public void setTranslations(List<VideoTranslationEntity> translations) {
		this.translations = translations;
	}

	public ContentStatus getStatus() {
		return this.status;
	}

	public void setStatus(ContentStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
