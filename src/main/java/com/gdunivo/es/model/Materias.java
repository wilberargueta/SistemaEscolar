package com.gdunivo.es.model;

public class Materias {

	private String codMateria;
	private String materia;

	public Materias() {

	}

	public Materias(String codMateria, String materia) {
		this.codMateria = codMateria;
		this.materia = materia;
	}

	public String getCodMateria() {
		return codMateria;
	}

	public void setCodMateria(String codMateria) {
		this.codMateria = codMateria;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	@Override
	public String toString() {
		return "Materias [codMateria=" + codMateria + ", materia=" + materia + "]";
	}

}
