package com.gdunivo.es.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Personal;
import com.gdunivo.es.repository.AlumnoClaseRepository;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.PersonalRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "docente")
@SessionScoped
public class IDocenteBean implements Serializable {

	private static final long serialVersionUID = -1451195755962640862L;

	private Personal personal = new Personal();

	private Clases clase = new Clases();
	private List<Clases> clases;
	private List<AlumnoClase> alumnoClases;
	private boolean claseSeleccionada = false;
	private boolean alumnoClaseSeleccionada = false;
	private ClasesRepository pClase = (ClasesRepository) RepositoryFactory.getRepository("Clases");
	private PersonalRepository pPersonal = (PersonalRepository) RepositoryFactory.getRepository("Personal");
	private AlumnoClaseRepository pAClase = (AlumnoClaseRepository) RepositoryFactory.getRepository("AlumnoClase");
	private AlumnoClase aClase = new AlumnoClase();

	public IDocenteBean() {
		this.personal = this.pPersonal.buscarPorCodigo(SessionUtils.getUsuario());
		this.clases = this.pClase.listadoClasesPorPersonal(personal);
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public List<Clases> getClases() {
		return clases;
	}

	public void setClases(List<Clases> clases) {
		this.clases = clases;
	}

	public List<AlumnoClase> getAlumnoClases() {
		return alumnoClases;
	}

	public void setAlumnoClases(List<AlumnoClase> alumnoClases) {
		this.alumnoClases = alumnoClases;
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}

	public boolean isClaseSeleccionada() {
		return claseSeleccionada;
	}

	public void setClaseSeleccionada(boolean claseSeleccionada) {
		this.claseSeleccionada = claseSeleccionada;
	}

	public AlumnoClase getaClase() {
		return aClase;
	}

	public void setaClase(AlumnoClase aClase) {
		this.aClase = aClase;
	}

	public boolean isAlumnoClaseSeleccionada() {
		return alumnoClaseSeleccionada;
	}

	public void setAlumnoClaseSeleccionada(boolean alumnoClaseSeleccionada) {
		this.alumnoClaseSeleccionada = alumnoClaseSeleccionada;
	}

	public void limpiarTodo() {

		this.clase = new Clases();
		this.aClase = new AlumnoClase();
		this.claseSeleccionada = false;
		this.alumnoClaseSeleccionada = false;
	}

	public void seleccionarClase(SelectEvent se) {
		this.clase = (Clases) se.getObject();
		this.claseSeleccionada = true;
		this.alumnoClases = this.pAClase.listaAlumnosPorClase(clase);

	}

	public void seleccionarAlumnoClase(SelectEvent se) {
		this.aClase = (AlumnoClase) se.getObject();
		this.alumnoClaseSeleccionada = true;

	}

	public void modificarNotas() {

		this.aClase.setNotaFinal((aClase.getNotaPP() + aClase.getNotaSP() + aClase.getNotaTP()) / 3);
		this.pAClase.actualizar(aClase);
		this.limpiarAlumnoClase();
	}
	
	public void limpiarAlumnoClase() {
		this.aClase = new AlumnoClase();
		this.alumnoClaseSeleccionada = false;
	}

}
