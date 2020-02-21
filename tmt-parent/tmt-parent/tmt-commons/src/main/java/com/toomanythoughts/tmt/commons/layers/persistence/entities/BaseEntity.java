package com.toomanythoughts.tmt.commons.layers.persistence.entities;

import static com.toomanythoughts.tmt.commons.utils.reflection.ClassReflectionUtils.retrievePropertyFields;
import static com.toomanythoughts.tmt.commons.utils.reflection.JpaEntityUtils.isRelationshipField;

import java.lang.reflect.Field;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity extends AuditableEntity {

	private static final long serialVersionUID = -4117313988601224098L;

	public abstract Integer getId();

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
				 * We usually don't swallow exceptions (we spit), but here we make an
				 * exception (pun intended) as not to kill the vibe of a good string representation.
				 */
				sB.append("Failed to fetch value of field! " + e.getClass().getName());
			}
		}
		return sB.toString();
	}
}
