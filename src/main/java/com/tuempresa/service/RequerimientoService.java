package com.tuempresa.service;

import java.io.Serializable;
import java.util.List;

import jakarta.inject.Inject;

import com.tuempresa.dao.RequerimientoDAO;
import com.tuempresa.model.EstadoRequerimiento;
import com.tuempresa.model.Requerimiento;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class RequerimientoService implements Serializable {

    @Inject
    private RequerimientoDAO requerimientoDAO;

    @Transactional
    public List<Requerimiento> listar() {
        return requerimientoDAO.findAll();
    }

    @Transactional
    public Requerimiento obtenerPorId(Integer id) {
        return requerimientoDAO.findById(id);
    }

    @Transactional
    public void guardar(Requerimiento requerimiento) {
        if (requerimiento.getId() == null) {
            requerimientoDAO.create(requerimiento);
        } else {
            requerimientoDAO.update(requerimiento);
        }
    }

    @Transactional
    public void anular(Integer id) {
        Requerimiento requerimiento = requerimientoDAO.findById(id);
        if (requerimiento != null) {
            requerimiento.setEstado(EstadoRequerimiento.Anulado);
            requerimientoDAO.update(requerimiento);
        }
    }

    @Transactional
    public void aprobar(Integer id) {
        Requerimiento requerimiento = requerimientoDAO.findById(id);
        if (requerimiento != null) {
            requerimiento.setEstado(EstadoRequerimiento.Aprobado);
            requerimientoDAO.update(requerimiento);
        }
    }

    @Transactional
    public void rechazar(Integer id) {
        Requerimiento requerimiento = requerimientoDAO.findById(id);
        if (requerimiento != null) {
            requerimiento.setEstado(EstadoRequerimiento.Rechazado);
            requerimientoDAO.update(requerimiento);
        }
    }
}
