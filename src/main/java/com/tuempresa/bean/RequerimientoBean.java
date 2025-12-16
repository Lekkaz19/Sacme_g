
package com.tuempresa.bean;

import com.tuempresa.model.Proyecto;
import com.tuempresa.model.Requerimiento;
import com.tuempresa.model.Usuario;
import com.tuempresa.service.ProyectoService;
import com.tuempresa.service.RequerimientoService;
import com.tuempresa.service.UsuarioService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class RequerimientoBean implements Serializable {

    @Inject
    private RequerimientoService requerimientoService;
    @Inject
    private ProyectoService proyectoService;
    @Inject
    private UsuarioService usuarioService;

    private List<Requerimiento> requerimientos;
    private Requerimiento requerimiento = new Requerimiento();
    private Requerimiento requerimientoSeleccionado;

    private List<Proyecto> proyectos;
    private List<Usuario> usuarios;

    private Usuario usuarioLogueado;

    @PostConstruct
    public void init() {
        // Simulando un usuario logueado
        usuarioLogueado = usuarioService.obtenerPorId(2); // Jefe de Area

        cargarRequerimientos();

        proyectos = proyectoService.listar();
        usuarios = usuarioService.listar();
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            requerimiento = requerimientoService.obtenerPorId(id);
        }
    }

    private void cargarRequerimientos() {
        List<Requerimiento> todos = requerimientoService.listar();
        if (usuarioLogueado.getRol().equals("Empleado")) {
            requerimientos = todos.stream()
                .filter(r -> r.getSolicitante().equals(usuarioLogueado))
                .collect(Collectors.toList());
        } else if (usuarioLogueado.getRol().equals("Jefe de Area")) {
            requerimientos = todos;
        } else if (usuarioLogueado.getRol().equals("Empleado de Compras")) {
            requerimientos = todos.stream()
                .filter(r -> r.getEstado() == com.tuempresa.model.EstadoRequerimiento.Aprobado)
                .collect(Collectors.toList());
        }
    }

    public List<Requerimiento> getRequerimientos() {
        return requerimientos;
    }

    public Requerimiento getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(Requerimiento requerimiento) {
        this.requerimiento = requerimiento;
    }

    public Requerimiento getRequerimientoSeleccionado() {
        return requerimientoSeleccionado;
    }

    public void setRequerimientoSeleccionado(Requerimiento requerimientoSeleccionado) {
        this.requerimientoSeleccionado = requerimientoSeleccionado;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void guardar() {
        requerimiento.setSolicitante(usuarioLogueado);
        requerimientoService.guardar(requerimiento);
        cargarRequerimientos();
        requerimiento = new Requerimiento();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento guardado"));
    }

    public void anular() {
        requerimientoService.anular(requerimientoSeleccionado.getId());
        cargarRequerimientos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento anulado"));
    }
    
    public void aprobar() {
        requerimientoService.aprobar(requerimientoSeleccionado.getId());
        cargarRequerimientos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento aprobado"));
    }

    public void rechazar() {
        requerimientoService.rechazar(requerimientoSeleccionado.getId());
        cargarRequerimientos();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Requerimiento rechazado"));
    }

    public void seleccionarRequerimiento(Requerimiento requerimiento) {
        this.requerimientoSeleccionado = requerimiento;
    }

    public boolean puedeEditar(Requerimiento req) {
        return req.getEstado() == com.tuempresa.model.EstadoRequerimiento.Pendiente &&
               (usuarioLogueado.getRol().equals("Jefe de Area") || usuarioLogueado.equals(req.getSolicitante()));
    }

    public boolean puedeAprobarRechazar(Requerimiento req) {
        return req.getEstado() == com.tuempresa.model.EstadoRequerimiento.Pendiente &&
               usuarioLogueado.getRol().equals("Jefe de Area");
    }
}
