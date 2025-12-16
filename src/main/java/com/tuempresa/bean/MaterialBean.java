package com.tuempresa.bean;

import com.tuempresa.model.EstadoEnum;
import com.tuempresa.model.Grupo;
import com.tuempresa.model.Material;
import com.tuempresa.model.Unidad;
import com.tuempresa.service.GrupoService;
import com.tuempresa.service.MaterialService;
import com.tuempresa.service.UnidadService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class MaterialBean implements Serializable {

    @Inject
    private MaterialService materialService;

    @Inject
    private GrupoService grupoService;

    @Inject
    private UnidadService unidadService;

    private List<Material> materiales;
    private List<Grupo> grupos;
    private List<Unidad> unidades;
    private Material selectedMaterial;
    private String filterNombre;
    private Grupo filterGrupo;
    private Unidad filterUnidad;

    @PostConstruct
    public void init() {
        this.materiales = materialService.getAll();
        this.grupos = grupoService.getActivos();
        this.unidades = unidadService.getActivos();
    }

    public void nuevo() {
        this.selectedMaterial = new Material();
        this.selectedMaterial.setEstado(EstadoEnum.Activo);
        this.selectedMaterial.setFechaCreacion(LocalDate.now());
    }

    public void guardar() {
        try {
            materialService.save(this.selectedMaterial);
            filtrar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Material guardado correctamente"));
            PrimeFaces.current().executeScript("PF('manageMaterialDialog').hide()");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
        }
    }

    public void editar(Material material) {
        this.selectedMaterial = material;
    }

    public void ver(Material material) {
        this.selectedMaterial = material;
    }

    public void eliminar(Material material) {
        try {
            materialService.delete(material);
            this.materiales = materialService.getAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Material eliminado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se puede eliminar."));
        }
    }

    public void cancelar() {
        this.selectedMaterial = null;
    }

    public void onGrupoChange() {
        if (selectedMaterial != null && selectedMaterial.getGrupo() != null) {
            // Optional Logic
        }
    }

    public void filtrar() {
        List<Material> all = materialService.getAll();
        this.materiales = all.stream()
                .filter(m -> filterNombre == null || filterNombre.isEmpty()
                        || m.getNombre().toLowerCase().contains(filterNombre.toLowerCase()))
                .filter(m -> filterGrupo == null || m.getGrupo().equals(filterGrupo))
                .filter(m -> filterUnidad == null || m.getUnidad().equals(filterUnidad))
                .collect(Collectors.toList());
    }

    // Getters and Setters
    public List<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(List<Material> materiales) {
        this.materiales = materiales;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    public List<Unidad> getUnidades() {
        return unidades;
    }

    public void setUnidades(List<Unidad> unidades) {
        this.unidades = unidades;
    }

    public Material getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(Material selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public String getFilterNombre() {
        return filterNombre;
    }

    public void setFilterNombre(String filterNombre) {
        this.filterNombre = filterNombre;
    }

    public Grupo getFilterGrupo() {
        return filterGrupo;
    }

    public void setFilterGrupo(Grupo filterGrupo) {
        this.filterGrupo = filterGrupo;
    }

    public Unidad getFilterUnidad() {
        return filterUnidad;
    }

    public void setFilterUnidad(Unidad filterUnidad) {
        this.filterUnidad = filterUnidad;
    }
}
