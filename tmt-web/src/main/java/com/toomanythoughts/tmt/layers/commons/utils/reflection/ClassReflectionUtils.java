package com.toomanythoughts.tmt.layers.commons.utils.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class ClassReflectionUtils {

	public static List<Annotation> retrieveActiveAnnotations(final Class<?> clazz) {
		final List<Annotation> list = new ArrayList<>();
		for (final Annotation annotation : clazz.getAnnotations()) {
			list.add(annotation);
		}
		return retrieveActiveParentAnnotations(clazz.getSuperclass(), list);
	}

	private static List<Annotation> retrieveActiveParentAnnotations(final Class<?> parentClass,
	                                                                final List<Annotation> list) {
		for (final Annotation annotation : parentClass.getAnnotations()) {
			if (annotation.getClass()
										.isAnnotationPresent(Inherited.class)) {
				list.add(annotation);
			}
		}
		if (!parentClass.getSuperclass()
				.equals(Object.class)) {
			retrieveActiveParentAnnotations(parentClass.getSuperclass(), list);
		}
		return list;
	}

	public static void removeFinalModifier(final Field field) throws Exception {
		field.setAccessible(true);
		final Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	}

	public static Class<?> retrieveTypeArgumentsOfField(final Field field,
																											final int numberOfGenericParameter) {
		return (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[numberOfGenericParameter];
	}

	public static boolean hasConstantWithName(final Class<?> clazz,
																						final String fieldName) {
		for (final Field field : retrieveConstantFields(clazz)) {
			if (field.getName()
								.equals(fieldName)) {
				return true;
			}
		}
		return false;
	}

	public static <E, V> void updateField(final E entity,
																				final V value,
																				final Field field) throws Exception {
		field.setAccessible(true);
		field.set(entity, value);
	}

	public static <E> void nullifyField(final E entity,
																			final Field field) throws Exception {
		if (field.getType()
							.isEnum() || field.getType()
																.isPrimitive()) {
			return;
		}
		field.setAccessible(true);
		field.set(entity, null);
	}

	public static List<Field> retrieveConstantFields(final Class<?> clazz) {
		final List<Field> fields = new ArrayList<Field>();
		for (final Field field : retrieveAllFields(clazz)) {
			if (!isFieldToBeProvidedAsPropertyField(field)) {
				fields.add(field);
			}
		}
		return fields;
	}

	public static List<Field> retrievePropertyFields(final Class<?> clazz) {
		final List<Field> fields = new ArrayList<Field>();
		for (final Field field : retrieveAllFields(clazz)) {
			if (isFieldToBeProvidedAsPropertyField(field)) {
				fields.add(field);
			}
		}
		return fields;
	}

	public static List<Field> retrieveAllFields(final Class<?> clazz) {
		final List<Field> fields = new ArrayList<Field>();
		Class<?> currentClass = clazz;
		while (!Object.class.equals(currentClass)) {
			for (final Field field : currentClass.getDeclaredFields()) {
				fields.add(field);
			}
			currentClass = currentClass.getSuperclass();
		}
		return fields;
	}

	public static List<Field> retrieveAnnotatedFields(final Class<?> objectClass,
																										final Class<? extends Annotation> annotationClass) {
		final List<Field> fields = new ArrayList<>();
		for (final Field field : retrieveAllFields(objectClass)) {
			final Annotation annotation = field.getAnnotation(annotationClass);
			if (annotation != null && annotation.annotationType()
																					.equals(annotationClass)) {
				fields.add(field);
			}
		}
		return fields;
	}

	public static Field retrieveField(final String fieldName,
																		final Object object) {
		Class<?> currentClass = object.getClass();
		while (!Object.class.equals(currentClass)) {
			for (final Field field : retrieveAllFields(object.getClass())) {
				if (field.getName()
									.equals(fieldName)) {
					return field;
				}
			}
			currentClass = currentClass.getSuperclass();
		}
		return null;
	}

	public static Field retrieveFieldFromClass(	final String fieldName,
																							final Class<?> clazz) {
		Class<?> currentClass = clazz;
		while (!Object.class.equals(currentClass)) {
			for (final Field field : retrieveAllFields(clazz)) {
				if (field.getName()
									.equals(fieldName)) {
					return field;
				}
			}
			currentClass = currentClass.getSuperclass();
		}
		return null;
	}

	public static Object retrieveFieldValue(final Field field,
																					final Object object) {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static Object retrieveFieldValue(final String fieldName,
																					final Object object) {
		try {
			for (final Field field : retrieveAllFields(object.getClass())) {
				if (field.getName()
									.equals(fieldName)) {
					return retrieveFieldValue(field, object);
				}
			}
			throw new RuntimeException("No field value found for field name '" + fieldName + "' in object: " + object.toString());
		} catch (final RuntimeException e) {
			throw e;
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean isFieldToBeProvidedAsPropertyField(final Field field) {
		return !field.getName()
									.startsWith("$") && !(Modifier.isStatic(field.getModifiers()) && Modifier.isFinal(field.getModifiers()));
	}
}
