package com.tuempresa.dao;

import com.tuempresa.model.Grupo;

public interface GrupoDAO extends GenericDAO<Grupo, Integer> {
    Grupo findByCodgrupo(String codgrupo);

    boolean existeCodgrupo(String codgrupo);
}
