package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.Requerimiento;

@ApplicationScoped
public class RequerimientoDAOImpl extends GenericDAOImpl<Requerimiento, Integer> implements RequerimientoDAO {
    
    public RequerimientoDAOImpl() {
        super(Requerimiento.class);
    }
}
