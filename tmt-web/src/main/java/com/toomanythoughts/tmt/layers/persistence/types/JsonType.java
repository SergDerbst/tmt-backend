package com.toomanythoughts.tmt.layers.persistence.types;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toomanythoughts.tmt.commons.exceptions.persistence.impl.JsonTypeReadingFailedRuntimeException;
import com.toomanythoughts.tmt.commons.exceptions.persistence.impl.JsonTypeWritingFailedRuntimeException;

public abstract class JsonType<T> implements UserType {

	private static final String UTF_8 = "UTF-8";

	private final Class<T> typeClass;

	protected JsonType(final Class<T> typeClass) {
		this.typeClass = typeClass;
	}

	@Override
	public int[] sqlTypes() {
		return new int[] { Types.JAVA_OBJECT };
	}

	@Override
	public Class<T> returnedClass() {
		return this.typeClass;
	}

	@Override
	public Object nullSafeGet(final ResultSet resultSet, final String[] names, final SharedSessionContractImplementor session, final Object owner) throws HibernateException, SQLException {
		final String content = resultSet.getString(names[0]);
		if (content == null) {
			return null;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(content.getBytes(UTF_8), this.returnedClass());
		} catch (final Exception e) {
			throw JsonTypeReadingFailedRuntimeException.prepare(e, content);
		}
	}

	@Override
	public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int index, final SharedSessionContractImplementor session) throws HibernateException, SQLException {
		if (value == null) {
			preparedStatement.setNull(index, Types.OTHER);
			return;
		}
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final StringWriter writer = new StringWriter();
			mapper.writeValue(writer, value);
			writer.flush();
			preparedStatement.setObject(index, writer.toString(), Types.OTHER);
		} catch (final Exception e) {
			throw JsonTypeWritingFailedRuntimeException.prepare(e, value);
		}
	}

	@Override
	public Object deepCopy(final Object value) throws HibernateException {
		try {
			final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(value);
			objectOutputStream.flush();
			objectOutputStream.close();
			byteArrayOutputStream.close();

			final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			return new ObjectInputStream(byteArrayInputStream).readObject();
		} catch (ClassNotFoundException | IOException e) {
			throw new HibernateException(e);
		}
	}

	@Override
	public boolean isMutable() {
		return true;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) this.deepCopy(value);
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return this.deepCopy(cached);
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return this.deepCopy(original);
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		if (x == null) {
			return y == null;
		}
		return x.equals(y);
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}
}
