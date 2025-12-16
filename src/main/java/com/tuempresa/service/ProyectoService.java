package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import com.tuempresa.dao.ProyectoDAO;
import com.tuempresa.model.Proyecto;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProyectoService implements Serializable {

    @Inject
    private ProyectoDAO proyectoDAO;

    @Transactional
    public List<Proyecto> listar() {
        return proyectoDAO.findAll();
    }

    @Transactional
    public Proyecto obtenerPorId(Integer id) {
        return proyectoDAO.findById(id);
    }

    @Transactional
    public void guardar(Proyecto proyecto) {
        if (proyecto.getId() == null) {
            proyectoDAO.create(proyecto);
        } else {
            proyectoDAO.update(proyecto);
        }
    }

    @Transactional
    public void eliminar(Integer id) {
        proyectoDAO.delete(id);
    }
}
