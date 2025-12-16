package com.tuempresa.dao;

import jakarta.enterprise.context.ApplicationScoped;

import com.tuempresa.model.Usuario;

@ApplicationScoped
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Integer> implements UsuarioDAO {
    
    public UsuarioDAOImpl() {
        super(Usuario.class);
    }
}
