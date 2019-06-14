package com.gdunivo.es.bean;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.AlumnoResponsable;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Clases;
import com.gdunivo.es.model.Materias;
import com.gdunivo.es.model.Responsables;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.AlumnoClaseRepository;
import com.gdunivo.es.repository.AlumnoResponsableRepository;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.ClasesRepository;
import com.gdunivo.es.repository.MateriasRepository;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.ResponsableRepository;
import com.gdunivo.es.repository.RolAlumnoRepository;

@ManagedBean(name = "alumnos")
@SessionScoped
public class AlumnosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Alumnos alumno = new Alumnos();
	private Alumnos alumnoSelec;
	private AlumnosRepository persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private RolAlumnoRepository rol = (RolAlumnoRepository) RepositoryFactory.getRepository("RolAlumno");
	private AlumnoClaseRepository pAClase = (AlumnoClaseRepository) RepositoryFactory.getRepository("AlumnoClase");
	private MateriasRepository pMaterias = (MateriasRepository) RepositoryFactory.getRepository("Materias");
	private ClasesRepository pClases = (ClasesRepository) RepositoryFactory.getRepository("Clases");
	private ResponsableRepository pRespon = (ResponsableRepository) RepositoryFactory.getRepository("Responsables");
	private Date fecha;
	private List<Alumnos> alumnos;
	private List<Clases> listaClases;
	private boolean seleccionado = false;
	private RolAlumno rAlumno = new RolAlumno();
	private Roles r = new Roles();
	private List<AlumnoClase> listaClasesAlumno = new ArrayList<>();
	private AlumnoClase aClase = new AlumnoClase();
	private Materias materia = new Materias();
	private List<Clases> clasesSeleccionadas = new ArrayList<>();
	private AlumnoResponsable aRespon =new  AlumnoResponsable();
	private AlumnoResponsableRepository pAR = (AlumnoResponsableRepository)RepositoryFactory.getRepository("AlumnoResponsable");

	private boolean claseSeleccionada = false;

	public AlumnosBean() {
		this.r.setId_Role(3);
		this.actualizarTabla();
	}

	public Alumnos getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}

	public Date getFecha() {
		return fecha;
	}
	

	public AlumnoResponsable getaRespon() {
		return aRespon;
	}

	public void setaRespon(AlumnoResponsable aRespon) {
		this.aRespon = aRespon;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public LocalDate convertDate(Date e) {
		return new java.sql.Date(e.getTime()).toLocalDate();
	}

	public Date convertToDateViaInstant(LocalDate dateToConvert) {

		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public List<Alumnos> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumnos> alumnos) {
		this.alumnos = alumnos;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	public Alumnos getAlumnoSelec() {
		return alumnoSelec;
	}

	public void setAlumnoSelec(Alumnos alumnoSelec) {
		this.alumnoSelec = alumnoSelec;
	}

	public RolAlumno getrAlumno() {
		return rAlumno;
	}

	public void setrAlumno(RolAlumno rAlumno) {
		this.rAlumno = rAlumno;
	}

	public List<Clases> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<Clases> listaClases) {
		this.listaClases = listaClases;
	}

	public List<AlumnoClase> getListaClasesAlumno() {
		return listaClasesAlumno;
	}

	public void setListaClasesAlumno(List<AlumnoClase> listaClasesAlumno) {
		this.listaClasesAlumno = listaClasesAlumno;
	}

	public AlumnoClase getaClase() {
		return aClase;
	}

	public void setaClase(AlumnoClase aClase) {
		this.aClase = aClase;
	}

	public Materias getMateria() {
		return materia;
	}

	public void setMateria(Materias materia) {
		this.materia = materia;
	}

	public boolean isClaseSeleccionada() {
		return claseSeleccionada;
	}

	public void setClaseSeleccionada(boolean claseSeleccionada) {
		this.claseSeleccionada = claseSeleccionada;
	}

	public List<Clases> getClasesSeleccionadas() {
		return clasesSeleccionadas;
	}

	public void setClasesSeleccionadas(List<Clases> clasesSeleccionadas) {
		this.clasesSeleccionadas = clasesSeleccionadas;
	}

	public void registrar() {
		this.alumno.setFechaNacimiento(convertDate(this.fecha));
		this.alumno = this.persis.guardar(this.alumno);
		this.rAlumno.setAlumno(this.alumno);
		this.rAlumno.setRol(this.r);
		this.rol.guardar(this.rAlumno);
		this.limpiarFormulario();
		this.actualizarTabla();
	}

	public void actualizarTabla() {
		this.alumnos = this.persis.obtenerLista();
	}

	public void limpiarFormulario() {
		this.seleccionado = false;
		this.alumno = new Alumnos();
		this.fecha = null;
		this.rAlumno = new RolAlumno();
	}

	public void seleccionarAlumno(SelectEvent event) {

		this.alumnoSelec = (Alumnos) event.getObject();
		this.alumno = this.alumnoSelec;
		this.fecha = this.convertToDateViaInstant(this.alumno.getFechaNacimiento());
		this.seleccionado = true;
	}

	public void actualizarAlumno() {
		this.alumno.setFechaNacimiento(convertDate(this.fecha));
		this.alumno = this.persis.actualizar(this.alumno);
		this.rAlumno.setAlumno(this.alumno);
		this.rAlumno.setRol(this.r);
		this.rol.actualizar(this.rAlumno);
		this.limpiarFormulario();
		this.actualizarTabla();

	}

	public void eliminarAlumno() {

		this.persis.eliminar(this.alumno);
		this.limpiarFormulario();
		this.actualizarTabla();
	}

	public String actualizarClases() {
		this.listaClasesAlumno = this.pAClase.clasesPorAlumno(this.alumno);
		return "inscripcion?faces-redirect=true";
	}

	public void seleccionarClase(SelectEvent event) {
		this.aClase = (AlumnoClase) event.getObject();
		this.claseSeleccionada = true;

	}

	public void limpiarClases() {
		this.aClase = new AlumnoClase();
		this.materia = new Materias();
		this.listaClases = null;
		this.claseSeleccionada = false;

	}

	public List<Materias> busquedaMateria(String query) {
		return this.pMaterias.busquedaPorMateria(query);
	}

	public void listadoClases() {

		this.listaClases = this.pClases.listadoClasesPorMateria(this.materia);
	}

	public void guardarClases() {

		this.clasesSeleccionadas.forEach(c -> {

			AlumnoClase ac = new AlumnoClase();
			ac.setAlumno(this.alumno);
			ac.setClase(c);
			this.pAClase.guardar(ac);

		});
		this.listaClasesAlumno = this.pAClase.clasesPorAlumno(this.alumno);
		this.limpiarClases();
	}

	public void eliminarMateria() {
		this.pAClase.eliminar(aClase);
		this.listaClasesAlumno = this.pAClase.clasesPorAlumno(this.alumno);
		this.limpiarClases();
	}

	public String regresar() {
		this.limpiarFormulario();
		return "alumnos";
	}

	public List<Responsables> busquedaResponsable(String query) {

		return this.pRespon.buscarPorNombre(query);
	}
	public void agregarAR() {
		this.aRespon.setAlumno(alumno);
		this.pAR.guardar(aRespon);
		
	}
}
