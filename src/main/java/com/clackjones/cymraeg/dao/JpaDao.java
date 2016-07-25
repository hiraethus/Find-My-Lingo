package com.clackjones.cymraeg.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JpaDao<K, E> implements Dao<K, E> {
    protected Class entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao () {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @Override
    public void remove(E entity) {
        entityManager.remove(entity);
    }

    @Override
    public E findById(K id) {
        return (E) entityManager.find(entityClass, id);
    }
}
