package io.xrates.backend.datamodel.dao;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.ReflectionUtils;

@Repository
public class AbstractDao<T extends Object> implements Dao<T> {

	@PersistenceContext
	private EntityManager entityManager;
	private Class<T> domainClass;
	
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = 
					(ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
	private String getDomainClassName() {
		return getDomainClass().getName();
	}
	
	@Override
	public void create(T t) {
		Method method = ReflectionUtils.findMethod(
				getDomainClass(), "setDateCreated",
				new Class[] { Date.class });
		if (method != null) {
			try {
				method.invoke(t, new Date(0));
			} catch(Exception e) { /* Ignore */ }
		}
		entityManager.persist(t);;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		return (T) entityManager.find(getDomainClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {
		return (T) entityManager.find(getDomainClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return entityManager
				.createQuery("from " + getDomainClassName())
				.getResultList();
	}

	@Override
	public void update(T t) {
		entityManager.persist(t);
	}

	@Override
	public void delete(T t) {
		entityManager.remove(t);;
	}

	@Override
	public void deleteById(Serializable id) {
		delete(load(id));;
	}

	@Override
	public void deleteAll() {
		entityManager
			.createQuery("delete " + getDomainClassName())
			.executeUpdate();
	}

	@Override
	public long count() {
		return (Long) entityManager
				.createQuery("select count(*) from " + getDomainClassName())
				.getSingleResult();
	}

	@Override
	public boolean exists(Serializable id) {
		return (get(id) != null);
	}

}