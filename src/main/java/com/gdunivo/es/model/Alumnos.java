package com.gdunivo.es.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Alumnos implements Serializable{


	private static final long serialVersionUID = 1L;
	private String codAlumno;
	private String nombre;
	private String apellido;
	private String direccion;
	private LocalDate fechaNacimiento;
	private String sexo;

	public Alumnos() {

	}

	public Alumnos(String codAlumno, String nombre, String apellido, String direccion, LocalDate fechaNacimiento,
			String sexo) {
		this.codAlumno = codAlumno;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
	}

	public String getCodAlumno() {
		return codAlumno;
	}

	public void setCodAlumno(String codAlumno) {
		this.codAlumno = codAlumno;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Alumnos [codAlumno=" + codAlumno + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion="
				+ direccion + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo + "]";
	}

}
