
package com.tuempresa.bean;

import com.tuempresa.model.Proveedor;
import com.tuempresa.service.ProveedorService;
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
public class ProveedorBean implements Serializable {

    @Inject
    private ProveedorService proveedorService;

    private List<Proveedor> proveedores;
    private Proveedor proveedor = new Proveedor();
    private Proveedor proveedorSeleccionado;

    @PostConstruct
    public void init() {
        proveedores = proveedorService.listar();
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            proveedor = proveedorService.obtenerPorId(id);
        }
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Proveedor getProveedorSeleccionado() {
        return proveedorSeleccionado;
    }

    public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
        this.proveedorSeleccionado = proveedorSeleccionado;
    }

    public void guardar() {
        proveedorService.guardar(proveedor);
        proveedores = proveedorService.listar();
        proveedor = new Proveedor();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor guardado"));
    }

    public void eliminar() {
        proveedorService.eliminar(proveedorSeleccionado.getId());
        proveedores = proveedorService.listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Proveedor eliminado"));
    }

    public void seleccionarProveedor(Proveedor proveedor) {
        this.proveedorSeleccionado = proveedor;
    }
}
