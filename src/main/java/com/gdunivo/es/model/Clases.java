package com.gdunivo.es.model;

public class Clases {

	private int idClase;
	private Personal personal;
	private Materias materia;
	private String seccion;
	private String dia;
	private String hora;
	private String turno;

	public Clases() {

	}

	public Clases(int idClase, Personal personal, Materias materia, String seccion, String dia, String hora,
			String turno) {
		this.idClase = idClase;
		this.personal = personal;
		this.materia = materia;
		this.seccion = seccion;
		this.dia = dia;
		this.hora = hora;
		this.turno = turno;
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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
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

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	@Override
	public String toString() {
		return "Clases [idClase=" + idClase + ", personal=" + personal + ", materia=" + materia + ", seccion=" + seccion
				+ ", dia=" + dia + ", hora=" + hora + ", turno=" + turno + "]";
	}

}
