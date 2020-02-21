package com.toomanythoughts.tmt.crawler.recipes.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.toomanythoughts.tmt.commons.layers.persistence.entities.BaseEntity;
import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.IngredientRepresentations;
import com.toomanythoughts.tmt.crawler.recipes.model.ingredients.StateOfMatter;
import com.toomanythoughts.tmt.crawler.recipes.persistence.types.IngredientRepresentationsType;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;

@Entity
@Table(name = "ingredients")
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
@TypeDef(name = "ingredients_representation_type", typeClass = IngredientRepresentationsType.class)
public class IngredientEntity extends BaseEntity {

	private static final long serialVersionUID = -6308348354423376006L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "ingredient_gen", sequenceName = "ingredient_seq")
	@Column(name = "ingredient_id")
	private Integer id;

	@Column(name = "key", unique = true, nullable = false, length = 50)
	private String key;

	@Column(name = "representations", columnDefinition = "jsonb")
	private IngredientRepresentations representations;

	@Enumerated(EnumType.STRING)
	@Column(name = "state_of_matter", nullable = false)
	@Type(type = "pgsql_enum")
	private StateOfMatter stateOfMatter;

	@Column(name = "cup_to_gram_ratio")
	private Double cupToGramRatio;

	@Column(name = "stick_to_gram_ratio")
	private Double stickToGramRatio;

	@Override
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public IngredientRepresentations getRepresentations() {
		return this.representations;
	}

	public void setRepresentations(IngredientRepresentations representations) {
		this.representations = representations;
	}

	public StateOfMatter getStateOfMatter() {
		return this.stateOfMatter;
	}

	public void setStateOfMatter(StateOfMatter stateOfMatter) {
		this.stateOfMatter = stateOfMatter;
	}

	public Double getCupToGramRatio() {
		return this.cupToGramRatio;
	}

	public void setCupToGramRatio(Double cupToGramRatio) {
		this.cupToGramRatio = cupToGramRatio;
	}

	public Double getStickToGramRatio() {
		return this.stickToGramRatio;
	}

	public void setStickToGramRatio(Double stickToGramRatio) {
		this.stickToGramRatio = stickToGramRatio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
