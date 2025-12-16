package com.tuempresa.bean;

import com.tuempresa.model.EstadoEnum;
import com.tuempresa.model.Unidad;
import com.tuempresa.service.UnidadService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UnidadBean implements Serializable {

    @Inject
    private UnidadService unidadService;

    private List<Unidad> unidades;
    private Unidad selectedUnidad;

    @PostConstruct
    public void init() {
        this.unidades = unidadService.getAll();
    }

    public void nuevo() {
        this.selectedUnidad = new Unidad();
        this.selectedUnidad.setEstado(EstadoEnum.Activo);
    }

    public void guardar() {
        try {
            unidadService.save(this.selectedUnidad);
            this.unidades = unidadService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unidad guardada correctamente"));
            PrimeFaces.current().executeScript("PF('manageUnidadDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void editar(Unidad unidad) {
        this.selectedUnidad = unidad;
    }

    public void eliminar(Unidad unidad) {
        try {
            unidadService.delete(unidad);
            this.unidades = unidadService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unidad eliminada"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede eliminar."));
        }
    }

    public void cancelar() {
        this.selectedUnidad = null;
    }

    // Getters and Setters
    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public Unidad getSelectedUnidad() {
        return selectedUnidad;
    }

    public void setSelectedUnidad(Unidad selectedUnidad) {
        this.selectedUnidad = selectedUnidad;
    }
}
