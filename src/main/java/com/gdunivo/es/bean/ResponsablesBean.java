package com.gdunivo.es.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.model.RolResponsable;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.ResponsableRepository;
import com.gdunivo.es.repository.RolResponsableRepository;

@ManagedBean(name = "responsables")
@SessionScoped
public class ResponsablesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Responsables responsable = new Responsables();
	private Responsables responsableSelec = new Responsables();
	private List<Responsables> responsables = new ArrayList<>();
	private boolean seleccionado = false;
	private ResponsableRepository persis = (ResponsableRepository) RepositoryFactory.getRepository("Responsables");
	private RolResponsableRepository rResponsablePersis = (RolResponsableRepository) RepositoryFactory
			.getRepository("RolResponsable");
	private RolResponsable rResponsable = new RolResponsable();
	private Roles rol = new Roles();

	public ResponsablesBean() {
		rol.setId_Role(4);
		this.actualizarTabla();
	}

	public Responsables getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsables responsable) {
		this.responsable = responsable;
	}

	public Responsables getResponsableSelec() {
		return responsableSelec;
	}

	public void setResponsableSelec(Responsables responsableSelec) {
		this.responsableSelec = responsableSelec;
	}

	public List<Responsables> getResponsables() {
		return responsables;
	}

	public void setResponsables(List<Responsables> responsables) {
		this.responsables = responsables;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
	

	public RolResponsable getrResponsable() {
		return rResponsable;
	}

	public void setrResponsable(RolResponsable rResponsable) {
		this.rResponsable = rResponsable;
	}

	public void registrar() {
		this.responsable = this.persis.guardar(this.responsable);
		this.rResponsable.setResponsable(this.responsable);
		this.rResponsable.setRole(this.rol);
		this.rResponsablePersis.guardar(rResponsable);
		this.limpiarFormulario();
		this.actualizarTabla();
	}

	public void actualizarTabla() {
		this.responsables = this.persis.obtenerListado();
	}

	public void limpiarFormulario() {
		this.seleccionado = false;
		this.responsable = new Responsables();
		this.rResponsable = new RolResponsable();

	}

	public void seleccionarResponsable(SelectEvent event) {

		this.responsableSelec = (Responsables) event.getObject();
		this.responsable = this.responsableSelec;
		this.seleccionado = true;
	}

	public void actualizarResponsable() {

		this.responsable = this.persis.actualizar(this.responsable);
		this.rResponsable.setResponsable(this.responsable);
		this.rResponsable.setRole(this.rol);
		this.rResponsablePersis.actualizar(rResponsable);
		this.limpiarFormulario();
		this.actualizarTabla();

	}

	public void eliminarResponsable() {

		this.persis.eliminar(this.responsable);
		this.limpiarFormulario();
		this.actualizarTabla();
	}

}
