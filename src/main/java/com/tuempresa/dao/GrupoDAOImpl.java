package com.tuempresa.dao;

import com.tuempresa.model.Grupo;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class GrupoDAOImpl extends GenericDAOImpl<Grupo, Integer> implements GrupoDAO {

    public GrupoDAOImpl() {
        super(Grupo.class);
    }

    @Override
    public Grupo findByCodgrupo(String codgrupo) {
        try {
            TypedQuery<Grupo> q = getEntityManager().createQuery("SELECT g FROM Grupo g WHERE g.codgrupo = :codgrupo",
                    Grupo.class);
            q.setParameter("codgrupo", codgrupo);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean existeCodgrupo(String codgrupo) {
        return findByCodgrupo(codgrupo) != null;
    }
}
