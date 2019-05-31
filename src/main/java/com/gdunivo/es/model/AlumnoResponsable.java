package com.gdunivo.es.model;

public class AlumnoResponsable {
	
	private int idAlumnoResponsable;
	private Alumnos alumno;
	private Responsables responsable;
	public AlumnoResponsable() {
	
	}
	public AlumnoResponsable(int idAlumnoResponsable, Alumnos alumno, Responsables responsable) {
		this.idAlumnoResponsable = idAlumnoResponsable;
		this.alumno = alumno;
		this.responsable = responsable;
	}
	public int getIdAlumnoResponsable() {
		return idAlumnoResponsable;
	}
	public void setIdAlumnoResponsable(int idAlumnoResponsable) {
		this.idAlumnoResponsable = idAlumnoResponsable;
	}
	public Alumnos getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}
	public Responsables getResponsable() {
		return responsable;
	}
	public void setResponsable(Responsables responsable) {
		this.responsable = responsable;
	}
	@Override
	public String toString() {
		return "AlumnoResponsable [idAlumnoResponsable=" + idAlumnoResponsable + ", alumno=" + alumno + ", responsable="
				+ responsable + "]";
	}
	
}
