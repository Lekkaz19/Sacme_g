package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import jakarta.inject.Inject;

import com.tuempresa.dao.RegistroCompraDAO;
import com.tuempresa.model.RegistroCompra;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RegistroCompraService implements Serializable {

    @Inject
    private RegistroCompraDAO registroCompraDAO;

    @Transactional
    public List<RegistroCompra> listar() {
        return registroCompraDAO.findAll();
    }
}
