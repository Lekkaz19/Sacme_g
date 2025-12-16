package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;
import com.tuempresa.model.Grupo;
import com.tuempresa.model.Material;
import com.tuempresa.model.Unidad;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class MaterialDAOImpl extends GenericDAOImpl<Material, Integer> implements MaterialDAO {

    public MaterialDAOImpl() {
        super(Material.class);
    }

    @Override
    public List<Material> findByGrupo(Grupo grupo) {
        TypedQuery<Material> q = this.em.createQuery("SELECT m FROM Material m WHERE m.grupo = :grupo",
                Material.class);
        q.setParameter("grupo", grupo);
        return q.getResultList();
    }

    @Override
    public List<Material> findByUnidad(Unidad unidad) {
        TypedQuery<Material> q = this.em.createQuery("SELECT m FROM Material m WHERE m.unidad = :unidad",
                Material.class);
        q.setParameter("unidad", unidad);
        return q.getResultList();
    }

    @Override
    public List<Material> findByNombre(String nombre) {
        TypedQuery<Material> q = this.em.createQuery("SELECT m FROM Material m WHERE m.nombre LIKE :nombre",
                Material.class);
        q.setParameter("nombre", "%" + nombre + "%");
        return q.getResultList();
    }

    @Override
    public List<Material> findByCodcorrelativo(String codcorrelativo) {
        TypedQuery<Material> q = this.em
                .createQuery("SELECT m FROM Material m WHERE m.codcorrelativo = :codcorrelativo", Material.class);
        q.setParameter("codcorrelativo", codcorrelativo);
        return q.getResultList();
    }
}
