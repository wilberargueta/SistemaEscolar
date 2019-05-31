package com.gdunivo.es.model;

public class Clases {

	private int idClase;
	private Personal personal;
	private Materias materia;
	private char seccion;
	private String dia;
	private String hora;

	public Clases() {

	}

	public Clases(int idClase, Personal personal, Materias materia, char seccion, String dia, String hora) {
		this.idClase = idClase;
		this.personal = personal;
		this.materia = materia;
		this.seccion = seccion;
		this.dia = dia;
		this.hora = hora;
	}

	public int getIdClase() {
		return idClase;
	}

	public void setIdClase(int idClase) {
		this.idClase = idClase;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public Materias getMateria() {
		return materia;
	}

	public void setMateria(Materias materia) {
		this.materia = materia;
	}

	public char getSeccion() {
		return seccion;
	}

	public void setSeccion(char seccion) {
		this.seccion = seccion;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Clases [idClase=" + idClase + ", personal=" + personal + ", materia=" + materia + ", seccion=" + seccion
				+ ", dia=" + dia + ", hora=" + hora + "]";
	}

}
