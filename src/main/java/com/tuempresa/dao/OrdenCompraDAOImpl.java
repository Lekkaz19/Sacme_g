package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.OrdenCompra;

@ApplicationScoped
public class OrdenCompraDAOImpl extends GenericDAOImpl<OrdenCompra, Integer> implements OrdenCompraDAO {
    
    public OrdenCompraDAOImpl() {
        super(OrdenCompra.class);
    }
}
