package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import com.tuempresa.dao.UsuarioDAO;
import com.tuempresa.model.Usuario;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UsuarioService implements Serializable {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Transactional
    public List<Usuario> listar() {
        return usuarioDAO.findAll();
    }

    @Transactional
    public Usuario obtenerPorId(Integer id) {
        return usuarioDAO.findById(id);
    }
}
