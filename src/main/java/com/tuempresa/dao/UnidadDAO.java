package com.tuempresa.dao;

import com.tuempresa.model.Unidad;

public interface UnidadDAO extends GenericDAO<Unidad, Integer> {
    Unidad findByAbreviatura(String abreviatura);

    boolean existeAbreviatura(String abreviatura);
}
