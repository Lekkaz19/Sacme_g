package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import com.tuempresa.model.Unidad;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class UnidadDAOImpl extends GenericDAOImpl<Unidad, Integer> implements UnidadDAO {

    public UnidadDAOImpl() {
        super(Unidad.class);
    }

    @Override
    public Unidad findByAbreviatura(String abreviatura) {
        try {
            TypedQuery<Unidad> q = em
                    .createQuery("SELECT u FROM Unidad u WHERE u.abreviatura = :abreviatura", Unidad.class);
            q.setParameter("abreviatura", abreviatura);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean existeAbreviatura(String abreviatura) {
        return findByAbreviatura(abreviatura) != null;
    }
}
