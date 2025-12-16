package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import com.tuempresa.dao.ProveedorDAO;
import com.tuempresa.model.Proveedor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProveedorService implements Serializable {

    @Inject
    private ProveedorDAO proveedorDAO;

    @Transactional
    public List<Proveedor> listar() {
        return proveedorDAO.findAll();
    }

    @Transactional
    public Proveedor obtenerPorId(Integer id) {
        return proveedorDAO.findById(id);
    }

    @Transactional
    public void guardar(Proveedor proveedor) {
        if (proveedor.getId() == null) {
            proveedorDAO.create(proveedor);
        } else {
            proveedorDAO.update(proveedor);
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        proveedorDAO.delete(id);
    }
}
