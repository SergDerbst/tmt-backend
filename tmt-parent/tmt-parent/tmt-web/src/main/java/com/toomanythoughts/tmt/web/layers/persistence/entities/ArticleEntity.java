package com.toomanythoughts.tmt.web.layers.persistence.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.web.layers.logic.Version;
import com.toomanythoughts.tmt.web.layers.logic.model.article.ArticleContent;
import com.toomanythoughts.tmt.web.layers.persistence.types.ArticleContentType;
import com.toomanythoughts.tmt.web.layers.persistence.types.VersionType;

@Entity
@Table(name = "articles")
@TypeDef(name = "version_type", typeClass = VersionType.class)
@TypeDef(name = "article_content_type", typeClass = ArticleContentType.class)
public class ArticleEntity extends BaseEntity {

	private static final long serialVersionUID = 8362885836780792814L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "article_gen", sequenceName = "articles_seq")
	@Column(name = "article_id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "owner_id")
	private UserEntity owner;

	@ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
	private List<UserEntity> authors;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published")
	private Date published;

	@Column(name = "version", columnDefinition = "jsonb", unique = true)
	@Type(type = "version_type")
	private Version version;

	@Column(name = "content", columnDefinition = "jsonb")
	@Type(type = "article_content_type")
	private ArticleContent content;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserEntity getOwner() {
		return this.owner;
	}

	public void setOwner(UserEntity owner) {
		this.owner = owner;
	}

	public List<UserEntity> getAuthors() {
		return this.authors;
	}

	public void setAuthors(List<UserEntity> authors) {
		this.authors = authors;
	}

	public Date getPublished() {
		return this.published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public ArticleContent getContent() {
		return this.content;
	}

	public void setContent(ArticleContent content) {
		this.content = content;
	}
}
