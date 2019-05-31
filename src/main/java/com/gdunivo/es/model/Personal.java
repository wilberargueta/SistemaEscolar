package com.gdunivo.es.model;

import java.time.LocalDate;

public class Personal {
	private String codPersonal;
	private String nombre;
	private String apellido;
	private String direccion;
	private String DUI;
	private String NIT;
	private LocalDate fechaNacimiento;

	public Personal() {

	}

	public Personal(String codPersonal, String nombre, String apellido, String direccion, String dUI, String nIT,
			LocalDate fechaNacimiento) {
		this.codPersonal = codPersonal;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		DUI = dUI;
		NIT = nIT;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getCodPersonal() {
		return codPersonal;
	}

	public void setCodPersonal(String codPersonal) {
		this.codPersonal = codPersonal;
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

	public String getDUI() {
		return DUI;
	}

	public void setDUI(String dUI) {
		DUI = dUI;
	}

	public String getNIT() {
		return NIT;
	}

	public void setNIT(String nIT) {
		NIT = nIT;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Personal [codPersonal=" + codPersonal + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion="
				+ direccion + ", DUI=" + DUI + ", NIT=" + NIT + ", fechaNacimiento=" + fechaNacimiento + "]";
	}

}
