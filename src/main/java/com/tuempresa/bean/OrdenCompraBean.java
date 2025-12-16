
package com.tuempresa.bean;

import com.tuempresa.model.OrdenCompra;
import com.tuempresa.model.Proveedor;
import com.tuempresa.service.OrdenCompraService;
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
public class OrdenCompraBean implements Serializable {

    @Inject
    private OrdenCompraService ordenCompraService;
    @Inject
    private ProveedorService proveedorService;

    private List<OrdenCompra> ordenesCompra;
    private OrdenCompra ordenCompra = new OrdenCompra();
    private OrdenCompra ordenCompraSeleccionada;

    private List<Proveedor> proveedores;

    @PostConstruct
    public void init() {
        ordenesCompra = ordenCompraService.listar();
        proveedores = proveedorService.listar();
        String idParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            ordenCompra = ordenCompraService.obtenerPorId(id);
        }
    }

    public List<OrdenCompra> getOrdenesCompra() {
        return ordenesCompra;
    }

    public OrdenCompra getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public OrdenCompra getOrdenCompraSeleccionada() {
        return ordenCompraSeleccionada;
    }

    public void setOrdenCompraSeleccionada(OrdenCompra ordenCompraSeleccionada) {
        this.ordenCompraSeleccionada = ordenCompraSeleccionada;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void guardar() {
        ordenCompraService.guardar(ordenCompra);
        ordenesCompra = ordenCompraService.listar();
        ordenCompra = new OrdenCompra();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Orden de Compra guardada"));
    }

    public void anular() {
        ordenCompraService.anular(ordenCompraSeleccionada.getId());
        ordenesCompra = ordenCompraService.listar();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Orden de Compra anulada"));
    }

    public void seleccionarOrdenCompra(OrdenCompra ordenCompra) {
        this.ordenCompraSeleccionada = ordenCompra;
    }
}
