package com.tuempresa.dao;

import com.tuempresa.model.Grupo;
import com.tuempresa.model.Material;
import com.tuempresa.model.Unidad;
import java.util.List;

public interface MaterialDAO extends GenericDAO<Material, Integer> {
    List<Material> findByGrupo(Grupo grupo);

    List<Material> findByUnidad(Unidad unidad);

    List<Material> findByNombre(String nombre);

    List<Material> findByCodcorrelativo(String codcorrelativo);
}
