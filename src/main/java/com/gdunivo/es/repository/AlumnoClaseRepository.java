package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.AlumnoClase;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Clases;

public interface AlumnoClaseRepository {

	public AlumnoClase guardar(AlumnoClase ac);

	public AlumnoClase actualizar(AlumnoClase ac);

	public boolean eliminar(AlumnoClase ac);

	public AlumnoClase primerNota(AlumnoClase pNota);

	public AlumnoClase segundaNota(AlumnoClase sNota);

	public AlumnoClase tercerNota(AlumnoClase tNota);

	public List<AlumnoClase> listaAlumnosPorClase(Clases c);

	public List<AlumnoClase> clasesPorAlumno(Alumnos a);
	

}
