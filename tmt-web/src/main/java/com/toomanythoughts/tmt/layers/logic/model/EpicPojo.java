package com.toomanythoughts.tmt.layers.logic.model;

import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrieveField;
import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrieveFieldValue;
import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrievePropertyFields;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public abstract class EpicPojo {

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
		final EpicPojo other = (EpicPojo) o;
		final EqualsBuilder equals = new EqualsBuilder();
		for (final Field thisField : retrievePropertyFields(this.getClass())) {
			final Field otherField = retrieveField(thisField.getName(), other);
			equals.append(retrieveFieldValue(thisField, this), retrieveFieldValue(otherField, other));
		}
		return equals.build();
	}

	@Override
	public int hashCode() {
		final HashCodeBuilder hashCode = new HashCodeBuilder();
		for (final Field field : retrievePropertyFields(this.getClass())) {
			hashCode.append(retrieveFieldValue(field, this));
		}
		return hashCode.build();
	}

	@Override
	public String toString() {
		final StringBuilder sB = new StringBuilder();
		sB.append("Class: " + this.getClass().getName() + "@" + System.identityHashCode(this));
		for (final Field field : retrievePropertyFields(this.getClass())) {
			field.setAccessible(true);
			sB.append("\n");
			sB.append("field: " + field.getName());
			sB.append(", type: " + field.getType().getName());
			try {
				sB.append(", value: " + field.getByte(this));
			} catch (final Exception e) {
				/*
				 * We usually don't swallow exceptions (we spit), but here we make an
				 * exception (pun intended) as not to kill the vibe of a good string representation.
				 */
				sB.append("Failed to fetch value of field! " + e.getClass().getName());
			}
		}
		return sB.toString();
	}
}
