
package com.tuempresa.bean;

import com.tuempresa.model.Proyecto;
import com.tuempresa.service.ProyectoService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProyectoBean implements Serializable {

    @Inject
    private ProyectoService proyectoService;

    private List<Proyecto> proyectos;
    private Proyecto proyecto = new Proyecto();
    private Proyecto proyectoSeleccionado;

    @PostConstruct
    public void init() {
        proyectos = proyectoService.listar();
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            proyecto = proyectoService.obtenerPorId(id);
        }
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public void guardar() {
        proyectoService.guardar(proyecto);
        proyectos = proyectoService.listar();
        proyecto = new Proyecto();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proyecto guardado"));
    }

    public void eliminar() {
        proyectoService.eliminar(proyectoSeleccionado.getId());
        proyectos = proyectoService.listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proyecto eliminado"));
    }

    public void seleccionarProyecto(Proyecto proyecto) {
        this.proyectoSeleccionado = proyecto;
    }
}
