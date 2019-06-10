package com.gdunivo.es.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.event.SelectEvent;

import com.gdunivo.es.exception.RecursoEliminadoException;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "materias")
@SessionScoped
public class MateriasBean implements Serializable {
	private Materias materia = new Materias();
	private Materias materiaSelec = new Materias();
	private List<Materias> lista = new ArrayList<>();
	private MateriasRepository persis = (MateriasRepository) RepositoryFactory.getRepository("Materias");
	private boolean seleccionado = false;
	private boolean tipoDAO = false;
	private static final long serialVersionUID = 1L;
	
	
	public MateriasBean() {
		this.actualizarTabla();
	}
	public Materias getMateria() {
		return materia;
	}

	public void setMateria(Materias materia) {
		this.materia = materia;
	}

	public Materias getMateriaSelec() {
		return materiaSelec;
	}

	public void setMateriaSelec(Materias materiaSelec) {
		this.materiaSelec = materiaSelec;
	}

	public List<Materias> getLista() {
		return lista;
	}

	public void setLista(List<Materias> lista) {
		this.lista = lista;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public boolean isTipoDAO() {
		return tipoDAO;
	}

	public void setTipoDAO(boolean tipoDAO) {
		this.tipoDAO = tipoDAO;
	}

	public void registrar() {
		try {
			this.persis.guardar(this.materia);
		} catch (RecursoEliminadoException e) {
			System.out.println(e.getMessage());
		}

		
		this.limpiarFormulario();
		this.actualizarTabla();
	}

	public void actualizarTabla() {
		this.lista = this.persis.obtenerLista();
	}

	public void limpiarFormulario() {
		this.seleccionado = false;
		this.materia = new Materias();
	}
	public void seleccionarMateria(SelectEvent event) {

		this.materiaSelec = (Materias) event.getObject();
		this.materia = this.materiaSelec;
		this.seleccionado = true;
	}
	public void actualizarMateria() {
		this.materia = this.persis.actualizar(this.materia);
		this.limpiarFormulario();
		this.actualizarTabla();

	}

	public void eliminarMateria() {

		this.persis.eliminar(this.materia);
		this.limpiarFormulario();
		this.actualizarTabla();
	}

}
