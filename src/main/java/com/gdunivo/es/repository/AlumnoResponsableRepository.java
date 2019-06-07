package com.gdunivo.es.repository;

import java.util.List;

import com.gdunivo.es.model.AlumnoResponsable;
import com.gdunivo.es.model.Alumnos;
import com.gdunivo.es.model.Responsables;

public interface AlumnoResponsableRepository {

	public AlumnoResponsable guardar(AlumnoResponsable ar);

	public AlumnoResponsable actualizar(AlumnoResponsable ar);

	public boolean eliminar(AlumnoResponsable ar);

	public List<AlumnoResponsable> listado();

	public List<AlumnoResponsable> listadoPorResponsable(Responsables r);

	public AlumnoResponsable ResponsablePorAlumno(Alumnos a);

}
