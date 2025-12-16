package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.Proveedor;

@ApplicationScoped
public class ProveedorDAOImpl extends GenericDAOImpl<Proveedor, Integer> implements ProveedorDAO {
    
    public ProveedorDAOImpl() {
        super(Proveedor.class);
    }
}
