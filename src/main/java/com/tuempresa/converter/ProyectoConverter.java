package com.tuempresa.converter;

import com.tuempresa.model.Proyecto;
import com.tuempresa.service.ProyectoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class ProyectoConverter implements Converter<Proyecto> {

    @Inject
    private ProyectoService proyectoService;

    @Override
    public Proyecto getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return proyectoService.obtenerPorId(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Proyecto value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
}
