package com.tuempresa.converter;

import com.tuempresa.model.Usuario;
import com.tuempresa.service.UsuarioService;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class UsuarioConverter implements Converter<Usuario> {

    @Inject
    private UsuarioService usuarioService;

    @Override
    public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return usuarioService.obtenerPorId(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Usuario value) {
        if (value == null) {
            return "";
        }
        return String.valueOf(value.getId());
    }
}
