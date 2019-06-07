package com.gdunivo.es.bean;

import java.io.Serializable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.RolAlumno;
import com.gdunivo.es.model.Roles;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.RepositoryFactory;
import com.gdunivo.es.repository.RolAlumnoRepository;

@ManagedBean(name = "alumnos")
@ViewScoped
public class AlumnosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Alumnos alumno = new Alumnos();
	private Alumnos alumnoSelec;
	private AlumnosRepository persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
	private RolAlumnoRepository rol = (RolAlumnoRepository) RepositoryFactory.getRepository("RolAlumno");
	private Date fecha;
	private List<Alumnos> alumnos;
	private boolean seleccionado = false;
	private RolAlumno rAlumno = new RolAlumno();
	private Roles r = new Roles();

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

}
