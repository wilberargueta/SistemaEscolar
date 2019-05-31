package com.gdunivo.es.model;

public class RolAlumno {

	private int rolAlumno;
	private Roles rol;
	private Alumnos alumno;
	private String contraseña;
	public RolAlumno() {
	
	}
	public RolAlumno(int rolAlumno, Roles rol, Alumnos alumno, String contraseña) {
		this.rolAlumno = rolAlumno;
		this.rol = rol;
		this.alumno = alumno;
		this.contraseña = contraseña;
	}
	public int getRolAlumno() {
		return rolAlumno;
	}
	public void setRolAlumno(int rolAlumno) {
		this.rolAlumno = rolAlumno;
	}
	public Roles getRol() {
		return rol;
	}
	public void setRol(Roles rol) {
		this.rol = rol;
	}
	public Alumnos getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	@Override
	public String toString() {
		return "RolAlumno [rolAlumno=" + rolAlumno + ", rol=" + rol + ", alumno=" + alumno + ", contraseña="
				+ contraseña + "]";
	}
	
	
}
