package com.gdunivo.es.model;

public class Responsables {
	private String codResponsable;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;
	private String DUI;

	public Responsables() {
		
	}

	public Responsables(String codResponsable, String nombre, String apellido, String direccion, String telefono,
			String dUI) {
		this.codResponsable = codResponsable;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
		DUI = dUI;
	}

	public String getCodResponsable() {
		return codResponsable;
	}

	public void setCodResponsable(String codResponsable) {
		this.codResponsable = codResponsable;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDUI() {
		return DUI;
	}

	public void setDUI(String dUI) {
		DUI = dUI;
	}

	@Override
	public String toString() {
		return "Responsables [codResponsable=" + codResponsable + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", direccion=" + direccion + ", telefono=" + telefono + ", DUI=" + DUI + "]";
	}

}
