package com.tuempresa.service;

import com.tuempresa.dao.UnidadDAO;
import com.tuempresa.model.EstadoEnum;
import com.tuempresa.model.Unidad;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class UnidadService implements Serializable {

    @Inject
    private UnidadDAO unidadDAO;

    public List<Unidad> getAll() {
        return unidadDAO.findAll();
    }

    public List<Unidad> getActivos() {
        return unidadDAO.findAll().stream()
                .filter(u -> u.getEstado() == EstadoEnum.Activo)
                .collect(Collectors.toList());
    }

    public Unidad getById(Integer id) {
        return unidadDAO.findById(id);
    }

    @Transactional
    public void save(Unidad unidad) throws Exception {
        if (unidad.getId() == null) {
            // Nuevo - Validar abreviatura única
            if (unidadDAO.existeAbreviatura(unidad.getAbreviatura())) {
                throw new Exception("La abreviatura '" + unidad.getAbreviatura() + "' ya existe.");
            }
            unidadDAO.create(unidad);
        } else {
            // Edición
            unidadDAO.update(unidad);
        }
    }

    @Transactional
    public void delete(Unidad unidad) {
        unidadDAO.delete(unidad.getId());
    }
}