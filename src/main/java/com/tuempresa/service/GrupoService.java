package com.tuempresa.service;

import com.tuempresa.dao.GrupoDAO;
import com.tuempresa.model.EstadoEnum;
import com.tuempresa.model.Grupo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ApplicationScoped
public class GrupoService implements Serializable {

    @Inject
    private GrupoDAO grupoDAO;

    public List<Grupo> getAll() {
        return grupoDAO.findAll();
    }

    public List<Grupo> getActivos() {
        return grupoDAO.findAll().stream()
                .filter(g -> g.getEstado() == EstadoEnum.Activo)
                .collect(Collectors.toList());
    }

    public Grupo getById(Integer id) {
        return grupoDAO.findById(id);
    }

    @Transactional
    public void save(Grupo grupo) throws Exception {
        if (grupo.getId() == null) {
            // Nuevo - Validar código único
            if (grupoDAO.existeCodgrupo(grupo.getCodgrupo())) {
                throw new Exception("El código de grupo '" + grupo.getCodgrupo() + "' ya existe.");
            }
            grupoDAO.create(grupo);
        } else {
            // Edición
            grupoDAO.update(grupo);
        }
    }

    @Transactional
    public void delete(Grupo grupo) {
        grupoDAO.delete(grupo.getId());
    }
}