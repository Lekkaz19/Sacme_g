package com.tuempresa.converter;

import com.tuempresa.model.Unidad;
import com.tuempresa.service.UnidadService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@FacesConverter(value = "unidadConverter", managed = true)
public class UnidadConverter implements Converter<Unidad> {

    @Inject
    private UnidadService unidadService;

    @Override
    public Unidad getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return unidadService.getById(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Unidad object) {
        if (object != null) {
            return String.valueOf(object.getId());
        }
        return null;
    }
}
