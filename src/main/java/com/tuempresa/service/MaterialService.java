package com.tuempresa.service;

import com.tuempresa.dao.MaterialDAO;
import com.tuempresa.model.Material;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ApplicationScoped
public class MaterialService implements Serializable {

    @Inject
    private MaterialDAO materialDAO;

    public List<Material> getAll() {
        return materialDAO.findAll();
    }

    @Transactional
    public void save(Material material) throws Exception {
        if (material.getId() == null) {
            materialDAO.create(material);
        } else {
            materialDAO.update(material);
        }
    }

    @Transactional
    public void delete(Material material) {
        materialDAO.delete(material.getId());
    }
}