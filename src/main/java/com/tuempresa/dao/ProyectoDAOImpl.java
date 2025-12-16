package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.Proyecto;

@ApplicationScoped
public class ProyectoDAOImpl extends GenericDAOImpl<Proyecto, Integer> implements ProyectoDAO {
    
    public ProyectoDAOImpl() {
        super(Proyecto.class);
    }
}
