package com.gdunivo.es.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;

import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.repository.AlumnosRepository;
import com.gdunivo.es.repository.RepositoryFactory;

@ManagedBean(name = "alumnos")
@SessionScoped
public class AlumnosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Alumnos alumno = new Alumnos();
	private AlumnosRepository persis;
	private Date fecha;
	private List<Alumnos> alumnos;

	public AlumnosBean() {
		this.persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
		this.alumnos = this.persis.obtenerLista();
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

	public List<Alumnos> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumnos> alumnos) {
		this.alumnos = alumnos;
	}

	public void registrar() {
		this.alumno.setFechaNacimiento(convertDate(this.fecha));
		System.out.println(this.alumno);
		this.persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
		this.persis.guardar(this.alumno);
		this.alumno = new Alumnos();
		this.fecha = null;
		this.persis = (AlumnosRepository) RepositoryFactory.getRepository("Alumnos");
		this.alumnos = this.persis.obtenerLista();
	}

}
