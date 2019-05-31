package com.gdunivo.es.model;

public class AlumnoClase {
	private int idAlumnoClase;
	private Alumnos alumno;
	private Clases clase;
	private float notaPP;
	private float notaSP;
	private float notaTP;
	private float notaFinal;

	public AlumnoClase() {

	}

	public AlumnoClase(int idAlumnoClase, Alumnos alumno, Clases clase, float notaPP, float notaSP, float notaTP,
			float notaFinal) {
		this.idAlumnoClase = idAlumnoClase;
		this.alumno = alumno;
		this.clase = clase;
		this.notaPP = notaPP;
		this.notaSP = notaSP;
		this.notaTP = notaTP;
		this.notaFinal = notaFinal;
	}

	public int getIdAlumnoClase() {
		return idAlumnoClase;
	}

	public void setIdAlumnoClase(int idAlumnoClase) {
		this.idAlumnoClase = idAlumnoClase;
	}

	public Alumnos getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumnos alumno) {
		this.alumno = alumno;
	}

	public Clases getClase() {
		return clase;
	}

	public void setClase(Clases clase) {
		this.clase = clase;
	}

	public float getNotaPP() {
		return notaPP;
	}

	public void setNotaPP(float notaPP) {
		this.notaPP = notaPP;
	}

	public float getNotaSP() {
		return notaSP;
	}

	public void setNotaSP(float notaSP) {
		this.notaSP = notaSP;
	}

	public float getNotaTP() {
		return notaTP;
	}

	public void setNotaTP(float notaTP) {
		this.notaTP = notaTP;
	}

	public float getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(float notaFinal) {
		this.notaFinal = notaFinal;
	}

	@Override
	public String toString() {
		return "AlumnoClase [idAlumnoClase=" + idAlumnoClase + ", alumno=" + alumno + ", clase=" + clase + ", notaPP="
				+ notaPP + ", notaSP=" + notaSP + ", notaTP=" + notaTP + ", notaFinal=" + notaFinal + "]";
	}

}
