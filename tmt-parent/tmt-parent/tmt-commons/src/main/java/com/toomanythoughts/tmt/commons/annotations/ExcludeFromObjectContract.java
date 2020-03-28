package com.toomanythoughts.tmt.commons.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.toomanythoughts.tmt.commons.layers.logic.model.EpicPojo;

/**
 * <p>
 * Annotation to be used on fields of {@link EpicPojo} objects to exclude them
 * from object contract methods {@link Object#equals}, {@link Object#hashCode},
 * and {@link Object#toString}. By default all three methods will be excluded
 * from these contract methods. The idea is to prevent infinite loops when two
 * objects reference each other.
 * </p>
 *
 * @author Sergio Weigel
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcludeFromObjectContract {
	boolean fromEquals() default true;
	boolean fromToString() default true;
	boolean fromHashCode() default true;
}
