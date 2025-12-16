
package com.tuempresa.bean;

import com.tuempresa.model.RegistroCompra;
import com.tuempresa.service.RegistroCompraService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class RegistroCompraBean implements Serializable {

    @Inject
    private RegistroCompraService registroCompraService;

    private List<RegistroCompra> registros;

    @PostConstruct
    public void init() {
        registros = registroCompraService.listar();
    }

    public List<RegistroCompra> getRegistros() {
        return registros;
    }
}
