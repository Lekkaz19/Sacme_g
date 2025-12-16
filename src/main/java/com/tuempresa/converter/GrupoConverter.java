package com.tuempresa.converter;

import com.tuempresa.model.Grupo;
import com.tuempresa.service.GrupoService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@FacesConverter(value = "grupoConverter", managed = true)
public class GrupoConverter implements Converter<Grupo> {

    @Inject
    private GrupoService grupoService;

    @Override
    public Grupo getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && value.trim().length() > 0) {
            try {
                return grupoService.getById(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Grupo object) {
        if (object != null) {
            return String.valueOf(object.getId());
        }
        return null;
    }
}
