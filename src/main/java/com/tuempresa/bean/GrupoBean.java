package com.tuempresa.bean;

import com.tuempresa.model.EstadoEnum;
import com.tuempresa.model.Grupo;
import com.tuempresa.service.GrupoService;
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
public class GrupoBean implements Serializable {

    @Inject
    private GrupoService grupoService;

    private List<Grupo> grupos;
    private Grupo selectedGrupo;

    @PostConstruct
    public void init() {
        this.grupos = grupoService.getAll();
    }

    public void nuevo() {
        this.selectedGrupo = new Grupo();
        this.selectedGrupo.setEstado(EstadoEnum.Activo);
    }

    public void guardar() {
        try {
            grupoService.save(this.selectedGrupo);
            this.grupos = grupoService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grupo guardado correctamente"));
            PrimeFaces.current().executeScript("PF('manageGrupoDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void editar(Grupo grupo) {
        this.selectedGrupo = grupo;
    }

    public void eliminar(Grupo grupo) {
        try {
            grupoService.delete(grupo);
            this.grupos = grupoService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Grupo eliminado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                    "No se puede eliminar (posiblemente en uso)"));
        }
    }

    public void cancelar() {
        this.selectedGrupo = null;
    }

    // Getters and Setters
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Grupo getSelectedGrupo() {
        return selectedGrupo;
    }

    public void setSelectedGrupo(Grupo selectedGrupo) {
        this.selectedGrupo = selectedGrupo;
    }
}
