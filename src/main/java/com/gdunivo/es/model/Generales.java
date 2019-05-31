package com.gdunivo.es.model;

public class Generales {

	private int idGenerales;
	private String nombreInstitucion;
	private String direccion;
	private String telefono;
	private String codigo;

	public Generales() {
	}

	public Generales(int idGenerales, String nombreInstitucion, String direccion, String telefono, String codigo) {
		this.idGenerales = idGenerales;
		this.nombreInstitucion = nombreInstitucion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.codigo = codigo;
	}

	public int getIdGenerales() {
		return idGenerales;
	}

	public void setIdGenerales(int idGenerales) {
		this.idGenerales = idGenerales;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Generales [idGenerales=" + idGenerales + ", nombreInstitucion=" + nombreInstitucion + ", direccion="
				+ direccion + ", telefono=" + telefono + ", codigo=" + codigo + "]";
	}

}
