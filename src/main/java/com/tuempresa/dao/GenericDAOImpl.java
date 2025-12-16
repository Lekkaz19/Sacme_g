package com.tuempresa.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

public abstract class GenericDAOImpl<T, ID> implements GenericDAO<T, ID> {

    @PersistenceContext(unitName = "bdipa")
    protected EntityManager em;

    private Class<T> entityClass;

    public GenericDAOImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    // Default constructor for CDI
    public GenericDAOImpl() {
    }

    // ⭐ AGREGAR ESTE MÉTODO ⭐
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public T create(T t) {
        this.em.persist(t);
        return t;
    }

    @Override
    public T update(T t) {
        return this.em.merge(t);
    }

    @Override
    public void delete(ID id) {
        T entity = findById(id);
        if (entity != null) {
            this.em.remove(entity);
        }
    }

    @Override
    public T findById(ID id) {
        return this.em.find(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        String qlString = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<T> q = this.em.createQuery(qlString, entityClass);
        return q.getResultList();
    }
}