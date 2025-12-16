package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.RegistroCompra;

@ApplicationScoped
public class RegistroCompraDAOImpl extends GenericDAOImpl<RegistroCompra, Integer> implements RegistroCompraDAO {
    
    public RegistroCompraDAOImpl() {
        super(RegistroCompra.class);
    }
}
