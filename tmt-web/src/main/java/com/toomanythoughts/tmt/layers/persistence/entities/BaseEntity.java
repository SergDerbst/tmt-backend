package com.toomanythoughts.tmt.layers.persistence.entities;

import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrieveField;
import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrieveFieldValue;
import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrievePropertyFields;
import static com.toomanythoughts.tmt.commons.utils.reflection.JpaEntityUtils.isRelationshipField;

import java.lang.reflect.Field;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity {

	private static final long serialVersionUID = -7065273098809483979L;

	public abstract Integer getId();

	@Override
	public boolean equals(final Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if (!this.getClass().equals(o.getClass())) {
			return false;
		}
		final BaseEntity other = (BaseEntity) o;
		final EqualsBuilder equals = new EqualsBuilder();
		for (final Field thisField : retrievePropertyFields(this.getClass())) {
			final Field otherField = retrieveField(thisField.getName(), other);
			equals.append(retrieveFieldValue(thisField, this), retrieveFieldValue(otherField, other));
		}
		return equals.build();
	}

	@Override
	public int hashCode() {
		final HashCodeBuilder hashCode = new HashCodeBuilder(17, 37);
		for (final Field field : retrievePropertyFields(this.getClass())) {
			hashCode.append(retrieveFieldValue(field, this));
		}
		return hashCode.build();
	}

	@Override
	public String toString() {
		final StringBuilder sB = new StringBuilder();
		sB.append("Entity: " + this.getClass().getName() + "@" + System.identityHashCode(this));
		for (final Field field : retrievePropertyFields(this.getClass())) {
			field.setAccessible(true);
			sB.append("\n");
			sB.append("field: " + field.getName());
			sB.append(", type: " + field.getType().getName());
			try {
				if (isRelationshipField(field)) {
					sB.append(", value: " + field.getType().getName() + "@" + System.identityHashCode(field.get(this)));
				} else {
					sB.append(", value: " + field.get(this));
				}
			} catch (final Exception e) {
				/*
				 * we usually don't swallow exceptions (we spit), but here we make an
				 * exception (pun intended) as not to kill the string representation
				 */
				sB.append("Failed to fetch value of field! " + e.getClass().getName());
			}
		}
		return sB.toString();
	}
}
