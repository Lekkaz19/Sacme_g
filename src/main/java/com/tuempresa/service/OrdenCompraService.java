package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import com.tuempresa.dao.OrdenCompraDAO;
import com.tuempresa.model.OrdenCompra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OrdenCompraService implements Serializable {

    @Inject
    private OrdenCompraDAO ordenCompraDAO;

    @Transactional
    public List<OrdenCompra> listar() {
        return ordenCompraDAO.findAll();
    }

    @Transactional
    public OrdenCompra obtenerPorId(Integer id) {
        return ordenCompraDAO.findById(id);
    }

    @Transactional
    public void guardar(OrdenCompra ordenCompra) {
        if (ordenCompra.getId() == null) {
            ordenCompraDAO.create(ordenCompra);
        } else {
            ordenCompraDAO.update(ordenCompra);
        }
    }

    @Transactional
    public void anular(Integer id) {
        OrdenCompra ordenCompra = ordenCompraDAO.findById(id);
        if (ordenCompra != null) {
            ordenCompra.setEstado(com.tuempresa.model.EstadoOrdenCompra.Anulada);
            ordenCompraDAO.update(ordenCompra);
        }
    }
}
