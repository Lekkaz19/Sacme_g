package com.tuempresa.converter;

import com.tuempresa.model.Proveedor;
import com.tuempresa.service.ProveedorService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class ProveedorConverter implements Converter<Proveedor> {

    @Inject
    private ProveedorService proveedorService;

    @Override
    public Proveedor getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return proveedorService.obtenerPorId(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proveedor value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
}
